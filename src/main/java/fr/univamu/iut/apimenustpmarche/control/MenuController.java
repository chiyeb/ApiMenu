package fr.univamu.iut.apimenustpmarche.control;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.services.handler.menu.MenuHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Menu")
public class MenuController {
    private final MenuHandler MenuHandler;

    @Autowired
    public MenuController(MenuHandler MenuHandler) {
        this.MenuHandler = MenuHandler;
    }

    @GetMapping("/all")
    public String getAllUsers() {
        return this.MenuHandler.getAllMenu().toString();
    }

    @GetMapping("{id}")
    public String getUserById(@PathVariable int id) {
        return this.MenuHandler.getMenuById(id).toString();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody Menu menu) {
        return this.MenuHandler.addMenu(menu).toString();
    }

    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody Menu menu) {
        return this.MenuHandler.updateMenu(menu).toString();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        this.MenuHandler.deleteMenu(id);
    }
}
