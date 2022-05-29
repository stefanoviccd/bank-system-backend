package rs.ac.bg.fon.banksystem.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.exception.ResourceNotFoundException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.model.Place;
import rs.ac.bg.fon.banksystem.model.Township;
import rs.ac.bg.fon.banksystem.repository.LegalEntityRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LegalEntityRepositoryImpl implements LegalEntityRepository {

    public LegalEntity save(LegalEntity legalEntity) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();

        List<Place> places = em.createQuery("select m from Place m where m.name=: name").setParameter("name", legalEntity.getStreet().getTownship().getPlace().getName())
                .getResultList();

        if (places.isEmpty()) {

            em.persist(legalEntity.getStreet().getTownship().getPlace());

        } else {
            legalEntity.getStreet().getTownship().setPlace(places.get(0));
        }
        List<Township> townships = (List<Township>) em.createQuery("select m from Township m where m.zipCode=: zipCode").setParameter("zipCode", legalEntity.getStreet().getTownship().getZipCode())
                .getResultList();

        if (townships.isEmpty()) {

            em.persist(legalEntity.getStreet().getTownship());

        } else {
            legalEntity.getStreet().setTownship(townships.get(0));

        }
        em.persist(legalEntity.getStreet());
        em.persist(legalEntity);
        System.out.println("Uspesno sacuvan objekat");


        return legalEntity;
    }

    public LegalEntity getById(Long id) {
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        LegalEntity dbEntity = em.find(LegalEntity.class, id);
        if (dbEntity == null) {
            throw new ResourceNotFoundException("Entity with ID " + id + " does not exist!");

        }
        return dbEntity;


    }

    public LegalEntity update(LegalEntity updateEntity) {
      EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        LegalEntity dbEntity=em.find(LegalEntity.class, updateEntity.getId());
        List<Place> places= em.createQuery("select m from Place m where m.name=: n").setParameter("n", updateEntity.getStreet().getTownship().getPlace().getName())
                .getResultList();
        if(places==null || places.isEmpty()){}
           // em.persist(updateEntity.getStreet().getTownship().getPlace());
        else{
            updateEntity.getStreet().getTownship().getPlace().setId(places.get(0).getId());
        }
        List<Township> townships = em.createQuery("select m from Township m where m.zipCode=: n").setParameter("n", updateEntity.getStreet().getTownship().getZipCode())
                .getResultList();
        if(townships==null || townships.isEmpty()){}
           // em.merge(updateEntity.getStreet().getTownship());
        else{
            updateEntity.getStreet().getTownship().setId(townships.get(0).getId());
        }

        em.merge(updateEntity);
        return updateEntity;
    }


    public void delete(LegalEntity entity) {
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        LegalEntity dbEntity=em.find(LegalEntity.class, entity.getId());
        if(dbEntity!=null)
        em.remove(dbEntity);


    }

    public List<LegalEntity> findAll() {
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        List<LegalEntity> entities = em.createQuery("select m from LegalEntity m")
                .getResultList();

        return entities;
    }

    public LegalEntity findByAccountNumber(String accountNumber) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        List<LegalEntity> entities = em.createQuery("select m from LegalEntity m where m.accountNumber=: accountNumber").setParameter("accountNumber", accountNumber)
                .getResultList();
        if (entities.isEmpty())
            return null;
        return entities.get(0);

    }

    public LegalEntity findByIdentification(String identificationNumber) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        List<LegalEntity> entities = em.createQuery("select m from LegalEntity m where m.identificationNumber=: in").setParameter("in", identificationNumber)
                .getResultList();
        if (entities.isEmpty())
            return null;
        return entities.get(0);

    }

    public List<LegalEntity> getByValue(String value) {
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        String searchingParameter="%"+value+"%";
        List<LegalEntity> entities = em.createQuery("select m from LegalEntity m where m.identificationNumber LIKE :value or  m.accountNumber LIKE :value or  m.name LIKE :value").setParameter("value", searchingParameter)
                .getResultList();

        return entities;
    }
}
