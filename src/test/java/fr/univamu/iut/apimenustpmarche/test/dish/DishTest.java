package fr.univamu.iut.apimenustpmarche.test.dish;

import fr.univamu.iut.apimenustpmarche.model.dish.Dish;
import org.junit.jupiter.api.Test;

public class DishTest {
    @Test
    public void testDish() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        assert dish.getName().equals("pate au fromage");
        assert dish.getDescription().equals("Pate au fromage de brebis ");
        assert dish.getPrice() == 8.99;
    }

    @Test
    public void testSetDish() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        dish.setName("pate au chocolat");
        dish.setDescription("Pate au chocolat de brebis ");
        dish.setPrice(9.99);
        assert dish.getName().equals("pate au chocolat");
        assert dish.getDescription().equals("Pate au chocolat de brebis ");
        assert dish.getPrice() == 9.99;
    }

    @Test
    public void testSetDishName() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        dish.setName("pate au chocolat");
        assert dish.getName().equals("pate au chocolat");
    }

    @Test
    public void testSetDishDescription() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        dish.setDescription("Pate au chocolat de brebis ");
        assert dish.getDescription().equals("Pate au chocolat de brebis ");
    }

    @Test
    public void testSetDishPrice() {
        Dish dish = new Dish("pate au fromage", "Pate au fromage de brebis ", 8.99);
        dish.setPrice(9.99);
        assert dish.getPrice() == 9.99;
    }
}
