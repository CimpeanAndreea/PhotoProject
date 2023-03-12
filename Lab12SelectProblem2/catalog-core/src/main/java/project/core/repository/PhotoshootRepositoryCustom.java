package project.core.repository;

import project.core.model.Photoshoot;

import java.util.List;

public interface PhotoshootRepositoryCustom {
    List<Photoshoot> findAllWithClientsAndLocationJPQL();
    List<Photoshoot> findAllWithClientsAndLocationCriteriaAPI();
    List<Photoshoot> findAllWithClientsSQL();

    List<Photoshoot> findAllWithLocationJPQL();
    List<Photoshoot> findAllWithLocationCriteriaAPI();
    List<Photoshoot> findAllWithLocationSQL();

}
