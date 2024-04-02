package fr.univamu.iut.apimenustpmarche.repository;

import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.plate.Plate;
import org.springframework.data.repository.CrudRepository;

public interface PlateRepository  extends CrudRepository<Plate, Integer> {
}
