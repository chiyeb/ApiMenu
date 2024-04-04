package fr.univamu.iut.apimenustpmarche.model.user;

/**
 * La classe UserLogin représente les informations de connexion d'un utilisateur pour se connecter.
 * Elle contient le nom d'utilisateur et le mot de passe.
 */
public class LoginCredentials {
    /**
     * Le nom d'utilisateur.
     */
    private String username;

    /**
     * Le mot de passe de l'utilisateur.
     */
    private String password;

    /**
     * Construit une nouvelle instance de UserLogin avec le nom d'utilisateur et le mot de passe spécifiés.
     *
     * @param username Le nom d'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     */
    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Récupère le nom d'utilisateur.
     *
     * @return Le nom d'utilisateur.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Définit le nom d'utilisateur.
     *
     * @param username Le nouveau nom d'utilisateur.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Récupère le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le nouveau mot de passe pour l'utilisateur.
     *
     * @param password Le nouveau mot de passe.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
