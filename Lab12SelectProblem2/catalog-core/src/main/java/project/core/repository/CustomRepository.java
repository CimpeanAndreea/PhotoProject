package project.core.repository;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
public abstract class CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
}
