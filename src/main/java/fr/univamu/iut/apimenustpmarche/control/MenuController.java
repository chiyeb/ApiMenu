package fr.univamu.iut.apimenustpmarche.control;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.menu.MenuRequest;
import fr.univamu.iut.apimenustpmarche.model.user.LoginCredentials;
import fr.univamu.iut.apimenustpmarche.services.handler.menu.MenuHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la gestion des menus. Fournit des endpoints pour créer, récupérer, mettre à jour,
 * et supprimer des menus.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuHandler menuHandler;

    /**
     * Constructeur pour initialiser le contrôleur avec un gestionnaire de menus.
     *
     * @param menuHandler Le gestionnaire de menus utilisé pour les opérations sur les menus.
     */
    @Autowired
    public MenuController(MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
    }

    /**
     * Crée un nouveau menu et le stocke.
     *
     * @param menu Les détails du menu à créer.
     * @return ResponseEntity contenant le menu créé.
     */
    @PostMapping("/create")
    public ResponseEntity<Menu> createMenu(@RequestBody MenuRequest menu) {
        Menu createdMenu = menuHandler.addMenu(menu);
        return ResponseEntity.ok(createdMenu);
    }

    /**
     * Récupère tous les menus existants.
     *
     * @return ResponseEntity contenant une liste de tous les menus.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllMenu() {
        return ResponseEntity.ok(menuHandler.getAllMenu());
    }

    /**
     * Récupère un menu par son identifiant.
     *
     * @param id L'identifiant du menu à récupérer.
     * @return ResponseEntity contenant le menu demandé.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable int id) {
        return ResponseEntity.ok(menuHandler.getMenuById(id));
    }

    /**
     * Met à jour un menu existant avec de nouveaux détails.
     *
     * @param id   L'identifiant du menu à mettre à jour.
     * @param menu Les nouveaux détails du menu.
     * @return ResponseEntity contenant le menu mis à jour.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable int id, @RequestBody MenuRequest menu) {
        Menu updatedMenu = menuHandler.updateMenu(id, menu);
        return ResponseEntity.ok(updatedMenu);
    }

    /**
     * Supprime un menu par son identifiant.
     *
     * @param id L'identifiant du menu à supprimer.
     * @return ResponseEntity indiquant que la suppression a réussi.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable int id, @RequestBody LoginCredentials user) {
        System.out.println("User : " + user);
        menuHandler.deleteMenu(id, user);
        return ResponseEntity.noContent().build();
    }
}
