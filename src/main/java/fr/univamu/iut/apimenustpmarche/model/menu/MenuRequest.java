package fr.univamu.iut.apimenustpmarche.model.menu;

import java.util.List;

/**
 * Représente une requête pour créer ou modifier un menu, incluant les informations
 * d'authentification de l'utilisateur, les détails du menu et une liste des identifiants
 * de plats à inclure dans le menu.
 */
public class MenuRequest {

    private String user;
    private String password;
    private String name;
    private String description;
    private List<Integer> dishesId;

    /**
     * Construit une nouvelle requête MenuRequest avec les identifiants de l'utilisateur spécifiés,
     * le nom du menu, la description et une liste des identifiants de plats.
     *
     * @param user        Le nom d'utilisateur pour l'authentification.
     * @param password    Le mot de passe pour l'authentification.
     * @param name        Le nom du menu.
     * @param description La description du menu.
     * @param dishesId    Une liste d'identifiants correspondant aux plats inclus dans le menu.
     */
    public MenuRequest(String user, String password, String name, String description, List<Integer> dishesId) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.description = description;
        this.dishesId = dishesId;
    }

    /**
     * Obtient le nom d'utilisateur pour l'authentification.
     *
     * @return Le nom d'utilisateur.
     */
    public String getUser() {
        return user;
    }

    /**
     * Définit le nom d'utilisateur pour l'authentification.
     *
     * @param user Le nom d'utilisateur à définir.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtient le mot de passe pour l'authentification.
     *
     * @return Le mot de passe.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe pour l'authentification.
     *
     * @param password Le mot de passe à définir.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtient le nom du menu.
     *
     * @return Le nom du menu.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du menu.
     *
     * @param name Le nom du menu à définir.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient la description du menu.
     *
     * @return La description du menu.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du menu.
     *
     * @param description La description à définir pour le menu.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtient la liste des identifiants des plats du menu.
     *
     * @return La liste des identifiants des plats.
     */
    public List<Integer> getDishesId() {
        return dishesId;
    }

    /**
     * Définit la liste des identifiants des plats pour le menu.
     *
     * @param dishesId La liste des identifiants des plats à définir.
     */
    public void setDishesId(List<Integer> dishesId) {
        this.dishesId = dishesId;
    }
}
