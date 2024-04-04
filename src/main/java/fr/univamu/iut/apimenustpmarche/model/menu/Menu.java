package fr.univamu.iut.apimenustpmarche.model.menu;

import fr.univamu.iut.apimenustpmarche.model.dish.Dish;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * La classe Menu représente un menu dans le système de gestion des menus.
 * Elle contient des informations sur le menu, telles que son nom, sa description, son prix,
 * l'identifiant du créateur, ainsi qu'une liste des plats qui composent le menu.
 */
@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private double price;

    @Column(name = "Creator_Id")
    private int creator;

    /**
     * Liste des plats inclus dans ce menu.
     * plusieurs menus peuvent contenir plusieurs plats.
     */
    @ElementCollection
    @CollectionTable(name = "dishes_in_menus", joinColumns = @JoinColumn(name = "id_menus"))
    @Column(name = "id_dish")
    private List<Integer> DishesInBD = new ArrayList<>();


    @Transient
    private List<Integer> IdDishes;

    @Transient
    private List<Dish> dishes = new ArrayList<>();

    /**
     * Constructeur par défaut protégé utilisé par "JPA".
     */
    protected Menu() {
    }

    /**
     * Construit un nouveau menu avec le nom, la description et une liste d'identifiants de plats spécifiés.
     *
     * @param name        le nom du menu
     * @param description la description du menu
     * @param idDishes    la liste des identifiants de plats inclus dans le menu
     */
    public Menu(String name, String description, List<Integer> idDishes) {
        this.name = name;
        this.description = description;
        this.IdDishes = Objects.requireNonNullElseGet(idDishes, ArrayList::new);
    }

    /**
     * Retourne le nom du menu.
     *
     * @return le nom du menu
     */
    public String getName() {
        return name;
    }
    /**
     * Retourne les plats contenu dans un menu, récupéré dans la BD.
     *
     * @return La liste des IDs plats
     */

    public List<Integer> getDishesInBD() {
        return DishesInBD;
    }
    /**
     * Définit les plats contenu dans un menu, récupéré dans la BD.
     */
    public void setDishesInBD(List<Integer> dishesIdsInBD) {
        this.DishesInBD = dishesIdsInBD;
    }

    /**
     * Définit le nom du menu.
     *
     * @param name le nom à définir pour le menu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la description du menu.
     *
     * @return la description du menu
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du menu.
     *
     * @param description la description à définir pour le menu
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne le prix du menu.
     *
     * @return le prix du menu
     */
    public double getPrice() {
        return price;
    }

    /**
     * Définit le prix du menu. Cette méthode peut être utilisée pour mettre à jour
     * le prix total du menu après l'ajout ou la suppression de plats.
     *
     * @param price le prix à définir pour le menu
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retourne l'identifiant du créateur du menu.
     *
     * @return l'identifiant du créateur du menu
     */
    public int getCreator() {
        return creator;
    }

    /**
     * Définit l'identifiant du créateur du menu.
     *
     * @param creator l'identifiant du créateur à définir
     */
    public void setCreator(int creator) {
        this.creator = creator;
    }

    /**
     * Retourne la liste des plats inclus dans le menu.
     *
     * @return la liste des plats
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    /**
     * Définit la liste des plats inclus dans le menu. Cette méthode peut être utilisée pour
     * remplacer tous les plats actuellement dans le menu par une nouvelle liste de plats.
     *
     * @param dishes la liste des plats à définir pour le menu
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    /**
     * Ajoute un plat au menu. Cette méthode met également à jour le prix du menu
     * pour inclure le prix du plat ajouté.
     *
     * @param dish le plat à ajouter au menu
     */
    public void addDish(Dish dish) {
        dishes.add(dish);
        setPrice(getPrice() + dish.getPrice());
    }

    /**
     * Supprime un plat du menu. Cette méthode ne met pas à jour le prix du menu.
     *
     * @param dish le plat à supprimer du menu
     */
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    /**
     * Supprime tous les plats du menu. Cette méthode réinitialise également le prix du menu à 0.
     */
    public void removeAllDishes() {
        dishes.clear();
        setPrice(0);
    }


    /**
     * Retourne l'identifiant unique du menu.
     *
     * @return l'identifiant du menu
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du menu. Habituellement utilisé par le mécanisme de persistance.
     *
     * @param id l'identifiant à définir
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Définit la liste des identifiants de plats pour le menu. Utilisé pour la récupération et la gestion interne des plats.
     *
     * @param idDishes la liste des identifiants de plats à définir
     */
    public void setIdDishes(List<Integer> idDishes) {
        IdDishes = idDishes;
    }
    /**
     * Retourne la liste des identifiants des plats du menu. Utilisé principalement pour des opérations internes.
     *
     * @return la liste des identifiants des plats
     */
    public List<Integer> getIdDishes() {
        return IdDishes;
    }
    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", creator=" + creator +
                ", Dishes=" + dishes +
                '}';
    }
}
