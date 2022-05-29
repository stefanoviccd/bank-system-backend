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
    private final LegalEntityValidator addEntityValidator = new LegalEntityAddValidator();
    private final LegalEntityValidator updateEntityValidator = new LegalEntityUpdateValidator();
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
            if (entity == null) {
                throw new Exception("Entity is null.");
            }
            if (entity.getStreet() == null) {
                throw new Exception("Entity street is null.");
            }
            if (entity.getStreet().getTownship() == null) {
                throw new Exception("Entity township is null.");
            }
            if (entity.getStreet().getTownship().getPlace() == null) {
                throw new Exception("Entity place is null.");
            }

            addEntityValidator.validate(entity);
            repo.save(entity);
            em.getTransaction().commit();
            return entity;


        } catch (Exception e) {
        //    if (em.getTransaction().isActive())
                em.getTransaction().rollback();
                throw  e;
        }
       finally {
            em.close();
            // session.close();
            EntityManagerProvider.getInstance().closeSession();

        }



    }

    public LegalEntity getById(Long id) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            LegalEntity dbEntity = repo.getById(id);
            em.getTransaction().commit();
            return dbEntity;

        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }


    }

    public void delete(LegalEntity entity) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            deleteAllLegalEntityReports(entity);
            Long placeId = entity.getStreet().getTownship().getPlace().getId();
            Long townShipId = entity.getStreet().getTownship().getId();
            repo.delete(entity);
            List<Street> streets = streetRepository.findByTownshipId(townShipId);
            if (streets.isEmpty())
                townShipRepository.delete(entity.getStreet().getTownship());
            List<Township> townships = townShipRepository.findByPlaceId(placeId);
            if (townships.isEmpty())
                placeRepository.delete(entity.getStreet().getTownship().getPlace());
            em.getTransaction().commit();


        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }

    }

    private void deleteAllLegalEntityReports(LegalEntity entity) {
        cbRepository.deleteByEntityId(entity.getId());

    }

    public LegalEntity update(LegalEntity entity) throws Exception {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        if (entity == null) {
            throw new Exception("Entity to be updated is null!");
        }

        em.getTransaction().begin();
        try {
            updateEntityValidator.validate(entity);
            //   LegalEntity dbEntity=repo.getById(entity.getId());
            repo.update(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }
    }

    public List<LegalEntity> findAll() {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {

            List<LegalEntity> entities = repo.findAll();
            em.getTransaction().commit();
            return entities;
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }


    }

    public List<LegalEntity> getByValue(String value) throws ValidationException {
        if (value == null) {
            throw new ValidationException("Searching value is null!");
        }
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();

        try {
            List<LegalEntity> dbEntities;
            if (value.trim() == "") {
                dbEntities = repo.findAll();
                em.getTransaction().commit();
                return dbEntities;
            } else {
                dbEntities = repo.getByValue(value);
                em.getTransaction().commit();
                return dbEntities;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }


    }
}

