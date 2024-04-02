package fr.univamu.iut.apimenustpmarche.control;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.services.handler.menu.MenuHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuHandler MenuHandler;

    @Autowired
    public MenuController(MenuHandler MenuHandler) {
        this.MenuHandler = MenuHandler;
    }

    @PostMapping("/create")
    public String createMenu(@RequestBody Menu menu) {
        return this.MenuHandler.addMenu(menu).toString();
    }

    @GetMapping("/all")
    public String getAllMenu() {
        return this.MenuHandler.getAllMenu().toString();
    }

    @GetMapping("{id}")
    public String getMenuById(@PathVariable int id) {
        return this.MenuHandler.getMenuById(id).toString();
    }
    @PutMapping("/update/{id}")
    public String updateMenu(@RequestBody Menu menu) {
        return this.MenuHandler.updateMenu(menu).toString();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMenu(@PathVariable int id) {
        this.MenuHandler.deleteMenu(id);
    }
}
