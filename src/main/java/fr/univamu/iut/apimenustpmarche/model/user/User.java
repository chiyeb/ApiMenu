package fr.univamu.iut.apimenustpmarche.model.user;

import jakarta.persistence.*;

/**
 * Classe représentant un utilisateur dans le système de gestion des menus.
 * Contient les informations de base d'un utilisateur telles que l'identifiant, le nom d'utilisateur,
 * le mot de passe, l'email, et l'adresse de livraison.
 */
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String mail;

    private String delivery_address;

    /**
     * Constructeur par défaut protégé utilisé par JPA.
     */
    protected User() {

    }

    /**
     * Construit un nouvel utilisateur avec les informations fournies.
     *
     * @param username Le nom d'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @param mail L'email de l'utilisateur.
     * @param delivery_address L'adresse de livraison de l'utilisateur.
     */
    public User(String username, String password, String mail, String delivery_address) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.delivery_address = delivery_address;
    }

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param id L'identifiant à définir pour l'utilisateur.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom d'utilisateur.
     *
     * @return Le nom d'utilisateur.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Définit le nom d'utilisateur.
     *
     * @param username Le nom d'utilisateur à définir.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param password Le mot de passe à définir.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retourne l'email de l'utilisateur.
     *
     * @return L'email de l'utilisateur.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Définit l'email de l'utilisateur.
     *
     * @param mail L'email à définir pour l'utilisateur.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Retourne l'adresse de livraison de l'utilisateur.
     *
     * @return L'adresse de livraison de l'utilisateur.
     */
    public String getDelivery_address() {
        return delivery_address;
    }

    /**
     * Définit l'adresse de livraison de l'utilisateur.
     *
     * @param delivery_address L'adresse de livraison à définir.
     */
    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }
}
