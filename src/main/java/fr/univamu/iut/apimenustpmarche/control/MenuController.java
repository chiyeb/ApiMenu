package fr.univamu.iut.apimenustpmarche.control;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.services.handler.menu.MenuHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuHandler menuHandler;

    @Autowired
    public MenuController(MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
    }

    @PostMapping("/create")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu createdMenu = menuHandler.addMenu(menu);
        return ResponseEntity.ok(createdMenu);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMenu() {
        return ResponseEntity.ok(menuHandler.getAllMenu()); // Envoie la liste des menus avec un statut 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable int id) {
        return ResponseEntity.ok(menuHandler.getMenuById(id)); // Envoie le menu demandé avec un statut 200 OK
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable int id, @RequestBody Menu menu) {
        Menu updatedMenu = menuHandler.updateMenu(id, menu); // Assurez-vous que la méthode `updateMenu` dans `MenuHandler` accepte l'`id` et `menu` comme paramètres et retourne le menu mis à jour.
        return ResponseEntity.ok(updatedMenu); // Envoie le menu mis à jour avec un statut 200 OK
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable int id) {
        menuHandler.deleteMenu(id);
        return ResponseEntity.noContent().build(); // Renvoie un statut 204 No Content pour indiquer que la ressource a été supprimée avec succès
    }
}
