package fr.univamu.iut.apimenustpmarche.services.handler.menu;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;

import java.util.List;

public interface MenuHandlerInterface {
    public List<Menu> getAllMenu();
    public Menu getMenuById(int id);
    public Menu addMenu(Menu menu);
    public Menu updateMenu(Menu menu);
    public void deleteMenu(int id);

    //public List<Menu> getMenusByCreator(int creator);
}
