package fr.univamu.iut.apimenustpmarche.services.handler.menu;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;

import java.util.List;

public interface MenuHandlerInterface {
    List<Menu> getAllMenu();
    Menu getMenuById(int id);
    Menu addMenu(Menu menu);
    Menu updateMenu(int id, Menu menu);
    void deleteMenu(int id);

    //public List<Menu> getMenusByCreator(int creator);
}
