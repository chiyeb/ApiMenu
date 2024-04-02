//package fr.univamu.iut.apimenustpmarche.model.plates_in_menus;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "plates_in_menus")
//public class PlateInMenus {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_menus")
//    private int idMenus;
//    @Transient
//    private List<Integer> idPlates;
//
//    protected PlateInMenus() {
//    }
//
//    public PlateInMenus(int idMenus, List<Integer> idPlates) {
//        this.idMenus = idMenus;
//        this.idPlates = idPlates;
//    }
//
//    public int getIdMenus() {
//        return idMenus;
//    }
//
//    public List<Integer> getIdPlates() {
//        return idPlates;
//    }
//}
