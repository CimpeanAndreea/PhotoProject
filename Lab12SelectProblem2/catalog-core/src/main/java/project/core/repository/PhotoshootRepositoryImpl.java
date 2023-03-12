package project.core.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;
import project.core.model.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class PhotoshootRepositoryImpl extends CustomRepository implements PhotoshootRepositoryCustom{
    @Override
    public List<Photoshoot> findAllWithClientsAndLocationJPQL() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct p from Photoshoot p " +
                        "left join fetch p.clients c " +
                        "left join fetch p.location");

        List<Photoshoot> photoshoots = query.getResultList();
        return photoshoots;
    }

    @Override
    public List<Photoshoot> findAllWithClientsAndLocationCriteriaAPI() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Photoshoot> query = criteriaBuilder.createQuery(Photoshoot.class);
        query.distinct(Boolean.TRUE);
        Root<Photoshoot> root = query.from(Photoshoot.class);

        root.fetch(Photoshoot_.clients, JoinType.LEFT);
        root.fetch(Photoshoot_.location, JoinType.LEFT);

        List<Photoshoot> photoshoots = entityManager.createQuery(query).getResultList();

        return photoshoots;
    }

    @Override
    public List<Photoshoot> findAllWithClientsSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*},{p.*},{l.*} " +
                "from photoshoot p " +
                "left join client_photoshoots cp on p.id=cp.client_id " +
                "left join client c on cp.photoshoot_id=c.id " +
                "left join location l on p.location_id=l.id ")
                .addEntity("p", Photoshoot.class)
                .addJoin("cp", "p.clients")
                .addEntity("p", Photoshoot.class)
                .addEntity("p", Photoshoot.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Photoshoot> photoshoots = query.getResultList();


        return photoshoots;
    }

    @Override
    public List<Photoshoot> findAllWithLocationJPQL() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct p from Photoshoot p " +
                        "left join fetch p.location");

        List<Photoshoot> photoshoots = query.getResultList();
        return photoshoots;
    }

    @Override
    public List<Photoshoot> findAllWithLocationCriteriaAPI() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Photoshoot> query = criteriaBuilder.createQuery(Photoshoot.class);
        query.distinct(Boolean.TRUE);
        Root<Photoshoot> root = query.from(Photoshoot.class);
//        query.select(root);
        Fetch<Photoshoot, Location> photoshootLocationFetch = root.fetch(Photoshoot_.location, JoinType.LEFT);

        List<Photoshoot> photoshoots = entityManager.createQuery(query).getResultList();

        return photoshoots;
    }

    @Override
    @Transactional
    public List<Photoshoot> findAllWithLocationSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {p.*},{l.*} " +
                "from photoshoot p " +
                "left join location l on p.location_id=l.id ")
                .addEntity("p", Photoshoot.class)
                .addJoin("l", "p.location")
                .addEntity("p", Photoshoot.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Photoshoot> photoshoots = query.getResultList();


        return photoshoots;
    }


}
