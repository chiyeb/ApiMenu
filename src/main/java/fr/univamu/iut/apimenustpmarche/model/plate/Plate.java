package fr.univamu.iut.apimenustpmarche.model.plate;

import jakarta.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Table(name = "Plate")
public class Plate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;


    public Plate() {
        this.id = 0;
        this.name = "Plat inconnu";
        this.price = 0;
        this.description = "Description inconnue";
    }
    public Plate(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addPrice(double price) {
        this.price += price;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}
