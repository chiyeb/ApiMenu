package fr.univamu.iut.apimenustpmarche.model.menu;

import fr.univamu.iut.apimenustpmarche.model.plate.Plate;
import org.springframework.data.annotation.Id;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany
    @JoinTable(
            name = "Plates_In_Menus",
            joinColumns = @JoinColumn(name = "Id_Menus"),
            inverseJoinColumns = @JoinColumn(name = "Id_Plates")
    )
    private List<Plate> plates;

    protected Menu() {
    }
    public Menu(String name, String description, List<Plate> plates) {
        this.name = name;
        this.description = description;
        this.plates = plates;
        for (Plate plate : plates) {
            this.price += plate.getPrice();
        }
    }

    public int getCreator() {
        return creator;
    }
    public void setCreator(int creator) {
        this.creator = creator;
    }
    public List<Plate> getPlates() {
        return plates;
    }
    public void addPlate(Plate plate) {
        plates.add(plate);
    }
    public void removePlat(Plate plate) {
        plates.remove(plate);
    }
    public void removeAllPlates() {
        plates.clear();
    }
    public void setPlates(ArrayList<Plate> plates) {
        this.plates = plates;
    }
    public int getId() {
        return id;
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
        return "Menu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

}
