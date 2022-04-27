package rs.ac.bg.fon.banksystem.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.model.Street;

import javax.persistence.EntityManager;
import java.util.List;

public class StreetRepositoryImpl {
    public List<Street> findByTownshipId(Long townShipId) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();

        List<Street> streets = em.createQuery("select m from Street m where m.township.id=: id").setParameter("id", townShipId)
                .getResultList();
        return streets;

    }
}
