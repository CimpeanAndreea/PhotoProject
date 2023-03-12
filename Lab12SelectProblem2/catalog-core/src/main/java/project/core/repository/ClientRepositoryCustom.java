package project.core.repository;

import project.core.model.Client;

import java.util.List;

public interface ClientRepositoryCustom {
    List<Client> findAllWithPhotoshootsAndLocationJPQL();
    List<Client> findAllWithPhotoshootsAndLocationCriteriaAPI();
    List<Client> findAllWithPhotoshootsAndLocationSQL();

    List<Client> findAllWithPhotosootsJPQL();
    List<Client> findAllWithPhotoshootsCriteriaAPI();
    List<Client> findAllWithPhotoshootsSQL();
}
