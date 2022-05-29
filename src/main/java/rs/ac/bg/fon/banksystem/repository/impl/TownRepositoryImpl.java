package rs.ac.bg.fon.banksystem.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.Place;
import rs.ac.bg.fon.banksystem.model.Street;
import rs.ac.bg.fon.banksystem.model.Township;
import rs.ac.bg.fon.banksystem.repository.TownshipRepository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class TownRepositoryImpl implements TownshipRepository {
    public Township findByZipCode(Long zipCode){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        List<Township> townships =  em.createQuery("select m from Township m where m.zipCode=: n").setParameter("n", zipCode)
                .getResultList();
        if(townships.isEmpty())
            return null;
        return townships.get(0);
    }

    public void delete(Township township) {
        EntityManager em= EntityManagerProvider.getInstance().getEntityManager();
        em.remove(township);

    }

    public List<Township> findByPlaceId(Long placeId) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();

        List<Township> townShips = em.createQuery("select m from Township m where m.place.id=: id").setParameter("id", placeId)
                .getResultList();
        return townShips;

    }
}
