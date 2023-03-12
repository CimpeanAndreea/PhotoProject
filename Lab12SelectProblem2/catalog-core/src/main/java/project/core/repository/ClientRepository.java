package project.core.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.core.model.Client;

import java.util.List;

public interface ClientRepository extends Repository<Client, Long>, ClientRepositoryCustom {
    @Query(value = "SELECT c.id, c.name, c.email, c.phoneNumber FROM client c WHERE c.name LIKE %:name%", nativeQuery = true)
    List<Client> filterClientsByName(@Param("name") String name);


    @Query("select distinct c from Client c")
    @EntityGraph(value = "clientWithPhotoshoots", type= EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithPhotoshoots();

    @Query("select distinct c from Client c")
    @EntityGraph(value = "clientWithPhotoshootsAndLocation", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithPhotoshootsAndLocation();
}
