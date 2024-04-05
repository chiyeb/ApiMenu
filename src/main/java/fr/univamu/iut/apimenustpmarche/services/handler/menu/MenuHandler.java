package fr.univamu.iut.apimenustpmarche.services.handler.menu;

import com.google.gson.Gson;
import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.dish.Dish;
import fr.univamu.iut.apimenustpmarche.model.menu.MenuRequest;
import fr.univamu.iut.apimenustpmarche.model.user.User;
import fr.univamu.iut.apimenustpmarche.model.user.LoginCredentials;
import fr.univamu.iut.apimenustpmarche.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
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
    private static final Gson gson = new Gson();


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
     * Récupère un plat à partir d'une api externe.
     * @Param id L'id du plat à récupérer.
     * @return Le plat avec comme id l'id en paramètre.
     */
    public Dish getDishById(int id) {
        return webClient.get()
                .uri("/dish/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> gson.fromJson(response, Dish.class))
                .onErrorResume(e -> Mono.just(new Dish()))
                .block();
    }
    /**
     * Récupère tous les menus disponibles dans la base de données, avec leurs plats correspondants.
     *
     * @return Une liste de tous les menus, avec les plats de chaque menu.
     */
    public List<Menu> getAllMenu() {
        ArrayList<Menu> menus = new ArrayList<>();
        for (Menu menu : menuRepository.findAll()) {
            for (Integer dishId : menu.getDishesInBD()) {
                Dish dish = getDishById(dishId);
                assert dish != null;
                if (!Objects.equals(dish.getName(), "inconnu")) {
                    menu.addDish(dish);
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
        Menu menu =  menuRepository.findById(id).orElse(null);
        if (menu != null) {
            for (Integer dishId : menu.getDishesInBD()) {
                Dish dish = getDishById(dishId);
                assert dish != null;
                if (!Objects.equals(dish.getName(), "inconnu")) {
                    menu.addDish(dish);
                }
            }
        }
        return menu;
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
        LoginCredentials loginCredentials = new LoginCredentials(menuRequest.getUser(), menuRequest.getPassword());
        User user = getUserByLoginCredentials(loginCredentials);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or authentication failed");
        }
        Menu menu = new Menu(menuRequest.getName(), menuRequest.getDescription(), menuRequest.getDishesId());
        menu.setCreator(user.getId());
        System.out.println("Menu : " + menu.getIdDishes().size());
        for (Integer dishId : menu.getIdDishes()) {
            System.out.println("Dish id : " + dishId);
            Dish dish = getDishById(dishId);
            assert dish != null;
            if (!Objects.equals(dish.getName(), "inconnu")) {
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
        User user = getUserByLoginCredentials(new LoginCredentials(menuRequest.getUser(), menuRequest.getPassword()));
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or authentication failed");
        }
        if (menuBd.getCreator() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to update this menu");
        }
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
     * Récupère un User à partir d'une api externe.
     * @Param loginCredentials Le LoginCredentials avec comme attributs le username et le mot de passe.
     * @return Le User renvoyé par l'api.
     */
    public User getUserByLoginCredentials(LoginCredentials loginCredentials) {
        return webClient.post()
                .uri("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(loginCredentials)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        response -> Mono.error(new ResponseStatusException(response.statusCode(),
                                "User not found or authentication failed")))
                .bodyToMono(User.class)
                .block();
    }
    /**
     * Supprime un menu spécifique par son identifiant.
     *
     * @param id L'identifiant du menu à supprimer.
     */
    public void deleteMenu(int id, LoginCredentials userCredentials) {
        System.out.println("UserCredentials : " + userCredentials);
        User user = getUserByLoginCredentials(userCredentials);
        System.out.println("User : " + user);
        menuRepository.findById(id).ifPresentOrElse(menu -> {
            if (user == null) {
                System.out.println("User not found or authentication failed");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or authentication failed");
            }
            if (menu.getCreator() != user.getId()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to delete this menu");
            }
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found");
        });
        menuRepository.deleteById(id);
    }
}
