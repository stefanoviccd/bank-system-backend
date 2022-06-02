package rs.ac.bg.fon.banksystem.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.model.Place;
import rs.ac.bg.fon.banksystem.repository.PlaceRepository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class PlaceRepositoryImpl implements PlaceRepository {
    public Place findByName(String name, EntityManager em){
        List<Place> places =  em.createQuery("select m from Place m where m.name=: n").setParameter("n", name)
                .getResultList();
        if(places.isEmpty())
            return null;
        return places.get(0);
    }

    public void delete(Place place, EntityManager em) {
        em.remove(place);

    }
}
