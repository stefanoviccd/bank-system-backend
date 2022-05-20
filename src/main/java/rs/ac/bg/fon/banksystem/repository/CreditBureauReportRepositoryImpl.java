package rs.ac.bg.fon.banksystem.repository;

import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreditBureauReportRepositoryImpl implements CreditBureauReportRepository {
    @Override
    public void addCreditBureauReport(CreditBureauReport report) {
        EntityManager em= EntityManagerProvider.getInstance().getEntityManager();
        for(int i=0; i<report.getLoans().size(); i++){
            em.persist(report.getLoans().get(i));
        }

        try {
            em.persist(report);
        }
        catch (Exception e){
            System.out.println("NIJE MOGUCE SACUVATI IZVESTAJ.");
           // em.getTransaction().rollback();
            e.printStackTrace();
        }



    }

    @Override
    public List<CreditBureauReport> getAllReports() {
        EntityManager em= EntityManagerProvider.getInstance().getEntityManager();
        List<CreditBureauReport> reports=em.createQuery("select m from CreditBureauReport  m").getResultList();
        return reports;

    }

    @Override
    public void deleteReport(Long reportId) {
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        CreditBureauReport r=em.find(CreditBureauReport.class, reportId);
        em.remove(r);
    }

    @Override
    public List<CreditBureauReport> getByValue(String value) {
        System.out.println("VALUE: "+value);
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        String searchingParameter="%"+value+"%";

       return em.createQuery("select m from CreditBureauReport m where m.bankName LIKE :value or m.reportNum LIKE :value or m.legalEntity.name LIKE :value").setParameter("value", searchingParameter)
                .getResultList();

    }

    @Override
    public CreditBureauReport getReportById(Long id) {
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        return em.find(CreditBureauReport.class, id);
    }

    @Override
    public void update(CreditBureauReport report) {
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();
        CreditBureauReport dbReport=em.find(CreditBureauReport.class, report.getId());
       for(int i=0; i<report.getLoans().size(); i++){
           if(report.getLoans().get(i).getId()!=null){
               em.find(Loan.class, report.getLoans().get(i).getId());
               em.merge(report.getLoans().get(i));
           }
           else{
               em.persist(report.getLoans().get(i));
           }

       }

        em.merge(report);
       // return report;
    }

    @Override
    public CreditBureauReport findByReportNumber(String reportNum) {
        EntityManager em=EntityManagerProvider.getInstance().getEntityManager();

        List<CreditBureauReport> dbReports=em.createQuery("select m from CreditBureauReport m where m.reportNum= :value").setParameter("value", reportNum)
                .getResultList();
        return  dbReports.isEmpty() ? null : dbReports.get(0);


    }
}
