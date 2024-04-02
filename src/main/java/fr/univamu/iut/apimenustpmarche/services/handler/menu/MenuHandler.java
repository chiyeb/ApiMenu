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

@Component
public class MenuHandler implements MenuHandlerInterface{
    private final MenuRepository menuRepository;
    private final WebClient webClient;

    @Autowired
    public MenuHandler(MenuRepository menuRepository, WebClient webClient) {
        this.menuRepository = menuRepository;
        this.webClient = webClient;
    }

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
                    System.out.println(plate);
                }
            }
            menus.add(menu);
        }
        return menus;
    }

    public Menu getMenuById(int id) {
        return menuRepository.findById(id).orElse(null);
    }
    public List<Integer> getPlateIdsByMenuId(int menuId) {
        return menuRepository.findPlateIdsByMenuId(menuId);
    }
    public Menu addMenu(Menu menu) {
        for (int i = 0; i < menu.getPlatesId().size(); i++) {
            Mono<Plate> plate = webClient.get()
                    .uri("/plate/{id}", menu.getPlatesId().get(i))
                    .retrieve()
                    .bodyToMono(Plate.class);
            menu.addPlate(plate.block());
        }
        return menuRepository.save(menu);
    }
    public Menu updateMenu(Menu menu) {
        return this.addMenu(menu);
    }
    public void deleteMenu(int id) {
        menuRepository.deleteById(id);
    }

    //public List<Menu> getMenusByCreator(int creator) {
//        return menuRepository.findByCreator(creator);
//    }

}
