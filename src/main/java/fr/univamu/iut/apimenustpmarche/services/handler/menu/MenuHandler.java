package fr.univamu.iut.apimenustpmarche.services.handler.menu;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.plate.Plate;
import fr.univamu.iut.apimenustpmarche.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire de menus chargé de la manipulation et des opérations liées aux menus,
 * y compris l'interaction avec le "repository" des menus et le service pour les plats.
 */
@Component
public class MenuHandler implements MenuHandlerInterface {
    private final MenuRepository menuRepository;
    private final PlateHandlerInterface plateHandler;
    private final WebClient webClient;

    /**
     * Constructeur pour initialiser le gestionnaire de menus avec ses dépendances.
     *
     * @param menuRepository Le repository pour accéder aux données des menus.
     * @param plateHandler   Le gestionnaire de plats pour les opérations sur les plats.
     * @param webClient      Le client web pour appeler des services externes liés aux plats.
     */
    @Autowired
    public MenuHandler(MenuRepository menuRepository, PlateHandlerInterface plateHandler, WebClient webClient) {
        this.menuRepository = menuRepository;
        this.webClient = webClient;
        this.plateHandler = plateHandler;
    }

    /**
     * Récupère tous les menus disponibles dans la base de données, avec leurs plats correspondants.
     *
     * @return Une liste de tous les menus, avec les plats de chaque menu.
     */
    public List<Menu> getAllMenu() {
        ArrayList<Menu> menus = new ArrayList<>();
        for (Menu menu : menuRepository.findAll()) {
            menu.setPlatesId(getPlateIdsByMenuId(menu.getId()));
            menu.getPlatesId().forEach(System.out::println);
            for (Integer plateId : menu.getPlatesId()) {
                Mono<Plate> plateMono = webClient.get()
                        .uri("/plate/{id}", plateId)
                        .retrieve()
                        .bodyToMono(Plate.class)
                        .onErrorReturn(new Plate());
                Plate plate = plateMono.block();
                if (plate != null) {
                    menu.addPlate(plate);
                }
            }
            menus.add(menu);
        }
        return menus;
    }

    /**
     * Récupère un menu spécifique par son identifiant dans la base de données.
     *
     * @param id L'identifiant du menu à récupérer.
     * @return Le menu correspondant à l'identifiant, ou null si aucun menu n'a été trouvé.
     */
    public Menu getMenuById(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    /**
     * Récupère les identifiants des plats associés à un menu spécifique dans la base de données.
     *
     * @param menuId L'identifiant du menu pour lequel les identifiants des plats sont recherchés.
     * @return Une liste d'identifiants de plats.
     */
    public List<Integer> getPlateIdsByMenuId(int menuId) {
        return menuRepository.findPlateIdsByMenuId(menuId);
    }

    /**
     * Ajoute un nouveau menu, en incluant l'ajout des plats spécifiés au menu.
     *
     * @param menu Le menu à ajouter.
     * @return Le menu ajouté avec ses plats.
     */
    public Menu addMenu(Menu menu) {
        for (int i = 0; i < menu.getPlatesId().size(); i++) {
            Mono<Plate> plateMono = webClient.get()
                    .uri("/plate/{id}", menu.getPlatesId().get(i))
                    .retrieve()
                    .bodyToMono(Plate.class);
            Plate plate = plateMono.block();
            if (plate != null) {
                menu.addPlate(plate);
            }
        }
        for (Plate plate : menu.getPlates()) {
            plateHandler.addPlate(plate);
        }
        return menuRepository.save(menu);
    }

    /**
     * Met à jour un menu existant avec son identifiant.
     *
     * @param id   L'identifiant du menu à mettre à jour.
     * @param menu Le menu contenant les nouvelles informations.
     * @return Le menu mis à jour.
     */
    public Menu updateMenu(int id, Menu menu) {
        Menu menuBd = menuRepository.findById(id).orElseThrow(RuntimeException::new);
        menu.setId(id);
        if (menu.getName() == null) {
            menu.setName(menuBd.getName());
        }
        if (menu.getDescription() == null) {
            menu.setDescription(menuBd.getDescription());
        }
        if (menu.getPlates() == null) {
            menu.setPlates(menuBd.getPlates());
        }
        return addMenu(menu);
    }

    /**
     * Supprime un menu spécifique par son identifiant.
     *
     * @param id L'identifiant du menu à supprimer.
     */
    public void deleteMenu(int id) {
        menuRepository.deleteById(id);
        Menu menu = menuRepository.findById(id).orElse(null);
        assert menu != null;
        for (Plate plate : menu.getPlates()) {
            plateHandler.deletePlate(plate);
        }
    }
}
