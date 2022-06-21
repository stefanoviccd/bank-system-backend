package rs.ac.bg.fon.banksystem.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.model.Street;
import rs.ac.bg.fon.banksystem.repository.StreetRepository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class StreetRepositoryImpl implements StreetRepository {
    public List<Street> findByTownshipId(Long townShipId, EntityManager em) {
        List<Street> streets = em.createQuery("select m from Street m where m.township.id=: id").setParameter("id", townShipId)
                .getResultList();
        return streets;

    }

    @Override
    public void delete(Long id, EntityManager em) {
       Street s= em.find(Street.class, id);
       em.remove(s);

    }
}
