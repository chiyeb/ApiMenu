package fr.univamu.iut.apimenustpmarche.services.handler.menu;

import fr.univamu.iut.apimenustpmarche.model.plate.Plate;
import fr.univamu.iut.apimenustpmarche.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Gestionnaire de plats chargé de la manipulation et des opérations liées aux plats,
 * incluant l'ajout et la suppression de plats via le repository.
 */
@Component
public class PlateHandler implements PlateHandlerInterface {

    private final PlateRepository plateRepository;

    /**
     * Constructeur pour initialiser le gestionnaire de plats avec le "repository".
     *
     * @param plateRepository Le référentiel pour accéder aux données des plats.
     */
    @Autowired
    public PlateHandler(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    /**
     * Ajoute un plat au base de données s'il n'existe pas déjà. Si le plat existe, l'opération est ignorée.
     *
     * @param plate Le plat à ajouter à la base de données.
     */
    public void addPlate(Plate plate) {
        plateRepository.findById(plate.getId()).orElseGet(() -> plateRepository.save(plate));
    }

    /**
     * Supprime un plat spécifique dans la base de données.
     *
     * @param plate Le plat à supprimer de la base de donneées.
     */
    public void deletePlate(Plate plate) {
        plateRepository.delete(plate);
    }
}
