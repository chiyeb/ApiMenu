package fr.univamu.iut.apimenustpmarche.services.handler.menu;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.menu.MenuRequest;
import fr.univamu.iut.apimenustpmarche.model.user.User;
import fr.univamu.iut.apimenustpmarche.model.user.UserLogin;

import java.util.List;

public interface MenuHandlerInterface {
    List<Menu> getAllMenu();
    Menu getMenuById(int id);
    Menu addMenu(MenuRequest menuRequest);
    Menu updateMenu(int id, MenuRequest menuRequest);
    void deleteMenu(int id, UserLogin user);

    //public List<Menu> getMenusByCreator(int creator);
}
