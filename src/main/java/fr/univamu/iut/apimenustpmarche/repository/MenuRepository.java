package fr.univamu.iut.apimenustpmarche.repository;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository pour les menus, il permet de manipuler les données dans la base de données.
 * Utilise l'interface CrudRepository pour les opérations "CRUD" de base.
 */
@Repository
@EnableJpaRepositories
public interface MenuRepository extends CrudRepository<Menu, Integer> {

    @Query(value = "SELECT p.id_dish FROM dishes_in_menus p WHERE p.id_menus = :menuId", nativeQuery = true)
    List<Integer> findDishIdsByMenuId(@Param("menuId") Integer menuId);
}
