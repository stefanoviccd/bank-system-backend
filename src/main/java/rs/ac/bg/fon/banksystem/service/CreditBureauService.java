package rs.ac.bg.fon.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.repository.CreditBureauReportRepository;
import rs.ac.bg.fon.banksystem.repository.LegalEntityRepository;
import rs.ac.bg.fon.banksystem.validator.CreditBureauReportValidator;
import rs.ac.bg.fon.banksystem.validator.impl.CreditBureauAddValidator;
import rs.ac.bg.fon.banksystem.validator.impl.CreditBureauUpdateValidator;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CreditBureauService {
    @Autowired
    private  CreditBureauAddValidator addReportValidator;

    @Autowired
    private CreditBureauUpdateValidator updateReportValidator;
    @Autowired
    private CreditBureauReportRepository repo;
    @Autowired
    private LegalEntityRepository legalEntityRepository;

    public CreditBureauService() {

    }

    public void addBureauReport(CreditBureauReport report) throws ValidationException {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            addReportValidator.validate(report, em);
            report.setLegalEntity(legalEntityRepository.getById(report.getLegalEntity().getId(), em));
            repo.addCreditBureauReport(report, em);
            em.getTransaction().commit();

        } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;

        } finally {
                em.close();

        }


    }

    public List<CreditBureauReport> getAllReports() {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        List<CreditBureauReport> dbReports;

        try {
            em.getTransaction().begin();
            dbReports = repo.getAllReports(em);
            em.getTransaction().commit();
            return dbReports;

        } catch (Exception e) {
                em.getTransaction().rollback();
            e.printStackTrace();
            throw e;

        } finally {
            em.close();

        }

    }

    public void deleteReport(Long reportId) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();

        try {
            em.getTransaction().begin();
            repo.deleteReport(reportId, em);
            em.getTransaction().commit();


        } catch (Exception e) {
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();

        }

    }

    public List<CreditBureauReport> getByValue(String value) throws ValidationException {
        if (value == null) {
            throw new ValidationException("Vrednost za pretragu je null!");
        }
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();

        try {
            List<CreditBureauReport> dbReports;
            if (value.trim() == "") {
                dbReports = repo.getAllReports(em);
                em.getTransaction().commit();
                return dbReports;
            } else {
                dbReports = repo.getByValue(value, em);
                em.getTransaction().commit();
                return dbReports;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            
        }
    }

    public CreditBureauReport getById(Long id) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {

            CreditBureauReport dbReport = repo.getReportById(id, em);
            em.getTransaction().commit();
            return dbReport;

        } catch (
                Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            
        }

    }

    public CreditBureauReport update(CreditBureauReport report) throws Exception {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            updateReportValidator.validate(report, em);
            repo.update(report, em);
            em.getTransaction().commit();
            return report;
        } catch (Exception e) {
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
            
        }
    }
}
