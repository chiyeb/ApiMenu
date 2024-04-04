package fr.univamu.iut.apimenustpmarche.test.menu;

import fr.univamu.iut.apimenustpmarche.model.dish.Dish;
import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MenuTest {

    @Test
    public void testAddDish() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        Menu menu = new Menu("Menu du jour", "Menu du jour avec une entree, un plat et un dessert",
                Collections.singletonList(dish.getId()));
        menu.addDish(dish);
        List<Dish> dishes = menu.getDishes();
        assertTrue(dishes.contains(dish));
    }

    @Test
    public void testRemoveDish() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        Menu menu = new Menu("Menu du jour", "Menu du jour avec une entree, un plat et un dessert",
                Collections.singletonList(dish.getId()));
        menu.addDish(dish);
        menu.removeDish(dish);
        List<Dish> dishes = menu.getDishes();
        assertFalse(dishes.contains(dish));
    }

    @Test
    public void testRemoveAllDishes() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        Menu menu = new Menu("Menu du jour", "Menu du jour avec une entree, un plat et un dessert",
                Collections.singletonList(dish.getId()));
        menu.addDish(dish);
        menu.removeAllDishes();
        List<Dish> dishes = menu.getDishes();
        assertTrue(dishes.isEmpty());
    }

    @Test
    public void testGetPrice() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        Menu menu = new Menu("Menu du jour", "Menu du jour avec une entree, un plat et un dessert",
                Collections.singletonList(dish.getId()));
        menu.addDish(dish);
        assertEquals(menu.getPrice(), dish.getPrice(), 0.0);
    }

    @Test
    public void testAddMultipleDishes() {
        Dish dish1 = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        Dish dish2 = new Dish("pate au chocolat", "Pate au chocolat de brebis ", 9.99);
        Menu menu = new Menu("Menu du jour", "Menu du jour avec une entree, un plat et un dessert",
                List.of(dish1.getId(), dish2.getId()));
        menu.addDish(dish1);
        menu.addDish(dish2);
        List<Dish> dishes = menu.getDishes();
        assertTrue(dishes.contains(dish1));
        assertTrue(dishes.contains(dish2));
    }
}
