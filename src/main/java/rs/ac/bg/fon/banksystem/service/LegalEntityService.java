package rs.ac.bg.fon.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.model.Street;
import rs.ac.bg.fon.banksystem.model.Township;
import rs.ac.bg.fon.banksystem.repository.*;
import rs.ac.bg.fon.banksystem.validator.LegalEntityValidator;
import rs.ac.bg.fon.banksystem.validator.impl.LegalEntityAddValidator;
import rs.ac.bg.fon.banksystem.validator.impl.LegalEntityUpdateValidator;

import javax.persistence.EntityManager;
import java.util.List;
@Service
public class LegalEntityService {
    @Autowired
    private LegalEntityAddValidator addEntityValidator ;
    @Autowired
    private  LegalEntityUpdateValidator updateEntityValidator ;
    @Autowired
    private LegalEntityRepository repo;
    @Autowired
    private CreditBureauReportRepository cbRepository;

    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private TownshipRepository townShipRepository;
    @Autowired
    private PlaceRepository placeRepository;

    public LegalEntity save(LegalEntity entity) throws Exception {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();

        try {
            addEntityValidator.validate(entity, em);
            repo.save(entity, em);
            em.getTransaction().commit();
            return entity;


        } catch (Exception e) {
                em.getTransaction().rollback();
                throw  e;
        }
       finally {
            em.close();

        }



    }

    public LegalEntity getById(Long id) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            LegalEntity dbEntity = repo.getById(id, em);
            em.getTransaction().commit();
            return dbEntity;

        } catch (Exception e) {
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
            
        }


    }

    public void delete(LegalEntity entity) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            deleteAllLegalEntityReports(entity, em);
            Long placeId = entity.getStreet().getTownship().getPlace().getId();
            Long townShipId = entity.getStreet().getTownship().getId();
            Long streetId = entity.getStreet().getId();
            repo.delete(entity, em);
            streetRepository.delete(streetId, em);
            List<Street> streets = streetRepository.findByTownshipId(townShipId, em);
            if (streets.isEmpty())
                townShipRepository.delete(townShipId, em);
            List<Township> townships = townShipRepository.findByPlaceId(placeId, em);
            if (townships.isEmpty())
                placeRepository.delete(placeId, em);
            em.getTransaction().commit();


        } catch (Exception e) {
                em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            
        }

    }


    private void deleteAllLegalEntityReports(LegalEntity entity, EntityManager em) {
        cbRepository.deleteByEntityId(entity.getId(), em);

    }

    public LegalEntity update(LegalEntity entity) throws Exception {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            updateEntityValidator.validate(entity, em);
            repo.update(entity, em);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
            
        }
    }

    public List<LegalEntity> findAll() {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {

            List<LegalEntity> entities = repo.findAll(em);
            em.getTransaction().commit();
            return entities;
        } catch (Exception e) {
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
            
        }


    }

    public List<LegalEntity> getByValue(String value) throws ValidationException {
        if (value == null) {
            throw new ValidationException("Vrednost za pretragu je null!");
        }
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();

        try {
            List<LegalEntity> dbEntities;
            if (value.trim() == "") {
                dbEntities = repo.findAll(em);
                em.getTransaction().commit();
                return dbEntities;
            } else {
                dbEntities = repo.getByValue(value, em);
                em.getTransaction().commit();
                return dbEntities;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            
        }


    }
}

