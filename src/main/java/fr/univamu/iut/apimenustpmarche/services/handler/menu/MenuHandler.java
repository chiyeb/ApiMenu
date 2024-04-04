package fr.univamu.iut.apimenustpmarche.services.handler.menu;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.dish.Dish;
import fr.univamu.iut.apimenustpmarche.model.menu.MenuRequest;
import fr.univamu.iut.apimenustpmarche.model.user.UserLogin;
import fr.univamu.iut.apimenustpmarche.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Gestionnaire de menus chargé de la manipulation et des opérations liées aux menus,
 * y compris l'interaction avec le "repository" des menus et le service pour les plats.
 */
@Component
public class MenuHandler implements MenuHandlerInterface {
    private final MenuRepository menuRepository;
    private final WebClient webClient;

    /**
     * Constructeur pour initialiser le gestionnaire de menus avec ses dépendances.
     *
     * @param menuRepository Le repository pour accéder aux données des menus.
     * @param webClient      Le client web pour appeler des services externes liés aux plats.
     */
    @Autowired
    public MenuHandler(MenuRepository menuRepository,WebClient webClient) {
        this.menuRepository = menuRepository;
        this.webClient = webClient;
    }

    /**
     * Récupère tous les menus disponibles dans la base de données, avec leurs plats correspondants.
     *
     * @return Une liste de tous les menus, avec les plats de chaque menu.
     */
    public List<Menu> getAllMenu() {
        ArrayList<Menu> menus = new ArrayList<>();
        for (Menu menu : menuRepository.findAll()) {
            menu.setIdDishes(getDisheIdsByMenuId(menu.getId()));
            menu.getIdDishes().forEach(System.out::println);
            for (Integer dishId : menu.getDishesInBD()) {
                Mono<Dish> dishMono = webClient.get()
                        .uri("/dishes/{id}", dishId)
                        .retrieve()
                        .bodyToMono(Dish.class)
                        .onErrorReturn(new Dish());
                Dish dish = dishMono.block();
                assert dish != null;
                System.out.println("Dish "+ dish.getName());
                if (!Objects.equals(dish.getName(), "inconnu")) {
                    menu.addDish(dish);
                    System.out.println("Dish "+ dish.getName());
                }
            }
            menus.add(menu);
            System.out.println("Taille "+ menu.getDishes().size());
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
    public List<Integer> getDisheIdsByMenuId(int menuId) {
        return menuRepository.findDishIdsByMenuId(menuId);
    }

    /**
     * Ajoute un nouveau menu, en incluant l'ajout des plats spécifiés au menu.
     *
     * @param menuRequest Le menu à ajouter.
     * @return Le menu ajouté avec ses plats.
     */
    public Menu addMenu(MenuRequest menuRequest) {
        Menu menu = new Menu(menuRequest.getName(), menuRequest.getDescription(), menuRequest.getDishesId());
        for (int i = 0; i < menu.getIdDishes().size(); i++) {
            Mono<Dish> dishMono = webClient.get()
                    .uri("/dishes/{id}", menu.getIdDishes().get(i))
                    .retrieve()
                    .bodyToMono(Dish.class);
            Dish dish = dishMono.block();
            if (dish != null) {
                menu.addDish(dish);
            }
        }
        return menuRepository.save(menu);
    }

    /**
     * Met à jour un menu existant avec son identifiant.
     *
     * @param id   L'identifiant du menu à mettre à jour.
     * @param menuRequest Le menu contenant les nouvelles informations.
     * @return Le menu mis à jour.
     */
    public Menu updateMenu(int id, MenuRequest menuRequest) {
        Menu menu = new Menu(menuRequest.getName(), menuRequest.getDescription(), menuRequest.getDishesId());
        Menu menuBd = menuRepository.findById(id).orElseThrow(RuntimeException::new);
        menu.setId(id);
        if (menu.getName() == null) {
            menu.setName(menuBd.getName());
        }
        if (menu.getDescription() == null) {
            menu.setDescription(menuBd.getDescription());
        }
        if (menu.getDishes() == null) {
            menu.setDishes(menuBd.getDishes());
        }
        return addMenu(menuRequest);
    }

    /**
     * Supprime un menu spécifique par son identifiant.
     *
     * @param id L'identifiant du menu à supprimer.
     */
    public void deleteMenu(int id, UserLogin user) {
        menuRepository.deleteById(id);
    }
}
