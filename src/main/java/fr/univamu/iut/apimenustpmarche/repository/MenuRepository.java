package fr.univamu.iut.apimenustpmarche.repository;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.plate.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MenuRepository extends CrudRepository<Menu, Integer> {

    @Query(value = "SELECT p.id_plates FROM plates_in_menus p WHERE p.id_menus = :menuId", nativeQuery = true)
    List<Integer> findPlateIdsByMenuId(@Param("menuId") Integer menuId);

}
