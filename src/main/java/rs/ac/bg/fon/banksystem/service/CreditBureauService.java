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
    private final CreditBureauReportValidator addReportValidator;
    private final CreditBureauReportValidator updateReportValidator;
    @Autowired
    private CreditBureauReportRepository repo;
    @Autowired
    private LegalEntityRepository legalEntityRepository;

    public CreditBureauService() {
        addReportValidator = new CreditBureauAddValidator();
        updateReportValidator = new CreditBureauUpdateValidator();

    }

    public void addBureauReport(CreditBureauReport report) throws ValidationException {
        if (report == null) {
            throw new ValidationException("Report to be saved is null!!");
        }
        if (report.getReportNum() == null) {
            throw new ValidationException("Report does not have report number!");
        }
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            addReportValidator.validate(report);
            report.setLegalEntity(legalEntityRepository.getById(report.getLegalEntity().getId()));
            repo.addCreditBureauReport(report);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;

        } finally {
            if (em.isOpen())
                em.close();
            EntityManagerProvider.getInstance().closeSession();

        }


    }

    public List<CreditBureauReport> getAllReports() {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        List<CreditBureauReport> dbReports;

        try {
            em.getTransaction().begin();
            dbReports = repo.getAllReports();
            em.getTransaction().commit();
            return dbReports;

        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
            throw e;

        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();

        }

    }

    public void deleteReport(Long reportId) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();

        try {
            em.getTransaction().begin();
            repo.deleteReport(reportId);
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

    public List<CreditBureauReport> getByValue(String value) throws ValidationException {
        if (value == null) {
            throw new ValidationException("Searching value is null!");
        }
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();

        try {
            List<CreditBureauReport> dbReports;
            if (value.trim() == "") {
                dbReports = repo.getAllReports();
                em.getTransaction().commit();
                return dbReports;
            } else {
                dbReports = repo.getByValue(value);
                em.getTransaction().commit();
                return dbReports;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }
    }

    public CreditBureauReport getById(Long id) {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {

            CreditBureauReport dbReport = repo.getReportById(id);
            em.getTransaction().commit();
            System.out.println(dbReport);
            return dbReport;

        } catch (
                Exception e) {
            //    if(em.getTransaction().isActive())
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }

    }

    public CreditBureauReport update(CreditBureauReport report) throws Exception {
        EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
        if (report == null) {
            throw new Exception("Report to be updated is null!");
        }

        em.getTransaction().begin();
        try {
            updateReportValidator.validate(report);
            //   LegalEntity dbEntity=repo.getById(entity.getId());
            repo.update(report);
            em.getTransaction().commit();
            return report;
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }
    }
}
