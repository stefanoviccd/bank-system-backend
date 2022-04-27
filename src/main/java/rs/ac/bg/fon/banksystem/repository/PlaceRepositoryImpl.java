package rs.ac.bg.fon.banksystem.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.model.Place;

import javax.persistence.EntityManager;
import java.util.List;

public class PlaceRepositoryImpl {
    public Place findByName(String name){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        List<Place> places =  em.createQuery("select m from Place m where m.name=: n").setParameter("n", name)
                .getResultList();
        if(places.isEmpty())
            return null;
        return places.get(0);
    }

    public void delete(Place place) {
        EntityManager em= EntityManagerProvider.getInstance().getEntityManager();
        em.remove(place);

    }
}
