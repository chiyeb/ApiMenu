package fr.univamu.iut.apimenustpmarche.services.handler.menu;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.plate.Plate;
import fr.univamu.iut.apimenustpmarche.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuHandler {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuHandler(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    public Menu createMenu(String name, String description, List<Plate> plates) {
        Menu menu = new Menu(name, description, plates);
        return menuRepository.save(menu);
    }
    public List<Menu> getAllMenu() {
        return (List<Menu>) menuRepository.findAll();
    }
    public Menu getMenuById(int id) {
        return menuRepository.findById(id).orElse(null);
    }
    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }
    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }
    public void deleteMenu(int id) {
        menuRepository.deleteById(id);
    }

    public List<Menu> getMenusByCreator(int creator) {
        return menuRepository.findByCreator(creator);
    }

}
