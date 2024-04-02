package fr.univamu.iut.apimenustpmarche.model.user;

import jakarta.persistence.*;


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

    protected User() {

    }
    public User(String username, String password, String mail, String delivery_address) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.delivery_address = delivery_address;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }
}
