package project.core.repository;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;
import project.core.model.Client;
import project.core.model.Client_;
import project.core.model.Photoshoot;
import project.core.model.Photoshoot_;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class ClientRepositoryImpl extends CustomRepository implements ClientRepositoryCustom{
    @Override
    public  List<Client> findAllWithPhotoshootsAndLocationJPQL(){
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct c from Client c " +
                        "left join fetch c.photoshoots p " +
                        "left join fetch p.location");

        List<Client> clients = query.getResultList();
        return clients;
    }

    @Override
    public List<Client> findAllWithPhotoshootsAndLocationCriteriaAPI() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);
        Root<Client> root = query.from(Client.class);
//        query.select(root);
        Fetch<Client, Photoshoot> clientPhotoshootFetch = root.fetch(Client_.photoshoots, JoinType.LEFT);
        clientPhotoshootFetch.fetch(Photoshoot_.location, JoinType.LEFT);

        List<Client> clients = entityManager.createQuery(query).getResultList();

        return clients;
    }

    @Override
    @Transactional
    public List<Client> findAllWithPhotoshootsAndLocationSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*},{p.*},{l.*} " +
                "from client c " +
                "left join client_photoshoots cp on c.id=cp.client_id " +
                "left join photoshoot p on cp.photoshoot_id=p.id " +
                "left join location l on p.location_id=l.id ")
                .addEntity("c", Client.class)
                .addJoin("cp", "c.photoshoots")
                .addEntity("p", Photoshoot.class)
                .addJoin("l", "p.location")
                .addEntity("c", Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Client> clients = query.getResultList();


        return clients;
    }

    @Override
    public List<Client> findAllWithPhotosootsJPQL() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct c from Client c " +
                        "left join fetch c.photoshoots p ");

        List<Client> clients = query.getResultList();
        return clients;
    }

    @Override
    public List<Client> findAllWithPhotoshootsCriteriaAPI() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);
        Root<Client> root = query.from(Client.class);
//      query.select(root);
        Fetch<Client, Photoshoot> clientPhotoshootFetch = root.fetch(Client_.photoshoots, JoinType.LEFT);

        List<Client> clients = entityManager.createQuery(query).getResultList();

        return clients;
    }

    @Override
    public List<Client> findAllWithPhotoshootsSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*},{p.*} " +
                "from client c " +
                "left join client_photoshoots cp on c.id=cp.client_id " +
                "left join photoshoot p on cp.photoshoot_id=p.id ")
                .addEntity("c", Client.class)
                .addJoin("cp", "c.photoshoots")
                .addEntity("p", Photoshoot.class)
                .addEntity("c", Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Client> clients = query.getResultList();


        return clients;
    }

}
