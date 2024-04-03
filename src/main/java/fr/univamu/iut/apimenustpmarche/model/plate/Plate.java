package fr.univamu.iut.apimenustpmarche.model.plate;

import jakarta.persistence.*;

/**
 * Classe représentant un plat dans le système de gestion des menus.
 * Elle contient des informations de base sur chaque plat, telles que son identifiant, son nom,
 * sa description, et son prix.
 */
@Entity
@Table(name = "Plate")
public class Plate {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    /**
     * Constructeur par défaut qui initialise un plat avec des valeurs par défaut.
     * L'identifiant est initialisé à 0, le nom à "Plat inconnu", le prix à 0, et la description à "Description inconnue".
     */
    public Plate() {
        this.id = 0;
        this.name = "Plat inconnu";
        this.price = 0;
        this.description = "Description inconnue";
    }

    /**
     * Constructeur pour créer un nouveau plat avec les détails spécifiés.
     *
     * @param name        Le nom du plat.
     * @param description La description du plat.
     * @param price       Le prix du plat.
     */
    public Plate(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Retourne l'identifiant du plat.
     *
     * @return L'identifiant du plat.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant du plat.
     *
     * @param id L'identifiant à attribuer au plat.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Incrémente le prix du plat par la valeur spécifiée.
     *
     * @param price La valeur à ajouter au prix actuel du plat.
     */
    public void addPrice(double price) {
        this.price += price;
    }

    /**
     * Définit le nom du plat.
     *
     * @param name Le nom à attribuer au plat.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit la description du plat.
     *
     * @param description La description à attribuer au plat.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Définit le prix du plat.
     *
     * @param price Le prix à attribuer au plat.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retourne le nom du plat.
     *
     * @return Le nom du plat.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la description du plat.
     *
     * @return La description du plat.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le prix du plat.
     *
     * @return Le prix du plat.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retourne une représentation en chaîne de caractères du plat, incluant son identifiant, son nom, sa description, et son prix.
     *
     * @return Une chaîne représentant le plat.
     */
    @Override
    public String toString() {
        return "Plat{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}
