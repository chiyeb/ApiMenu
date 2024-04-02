package fr.univamu.iut.apimenustpmarche.model.menu;

import fr.univamu.iut.apimenustpmarche.model.plate.Plate;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "plates_in_menus",
            joinColumns = @JoinColumn(name = "id_menus", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_plates", referencedColumnName = "id")
    )
    private List<Plate> plates = new ArrayList<>();
    @Transient
    private List<Integer> IdPlates;
    protected Menu() {
    }
    public Menu(String name, String description, List<Integer> platesId) {
        this.name = name;
        this.description = description;
        this.IdPlates = Objects.requireNonNullElseGet(platesId, ArrayList::new);
//        for (Plate plate : platesId) {
//            this.price += plate.getPrice();
//        }
    }

    public List<Integer> getPlatesId() {
        return IdPlates;
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
        setPrice(getPrice() + plate.getPrice());
    }
    public void setPrice(double price) {
        this.price = price;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", creator=" + creator +
                ", plates=" + plates +
                '}';
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setPlatesId(List<Integer> platesId) {
        this.IdPlates = platesId;
    }

}
