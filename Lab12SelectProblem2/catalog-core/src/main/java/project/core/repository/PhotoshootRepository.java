package project.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import project.core.model.Client;
import project.core.model.Photoshoot;

import java.util.List;

public interface PhotoshootRepository extends Repository<Photoshoot, Long>, PhotoshootRepositoryCustom{
    @Query("select distinct p from Photoshoot p")
    @EntityGraph(value = "photoshootWithLocation", type= EntityGraph.EntityGraphType.LOAD)
    List<Photoshoot> findAllWithLocation();

    @Query("select distinct p from Photoshoot p")
    @EntityGraph(value = "photoshootWithClientsAndLocation", type = EntityGraph.EntityGraphType.LOAD)
    List<Photoshoot> findAllWithClientsAndLocation();
}
