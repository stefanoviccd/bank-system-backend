package rs.ac.bg.fon.banksystem.repository.impl;

import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.*;
import rs.ac.bg.fon.banksystem.repository.CreditBureauReportRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Repository
public class CreditBureauReportRepositoryImpl implements CreditBureauReportRepository {
    @Override
    public void addCreditBureauReport(CreditBureauReport report, EntityManager em) {
        for(int i=0; i<report.getLoans().size(); i++){
            em.persist(report.getLoans().get(i));
        }

        em.persist(report);



    }

    @Override
    public List<CreditBureauReport> getAllReports(EntityManager em) {
        List<CreditBureauReport> reports=em.createQuery("select m from CreditBureauReport  m").getResultList();
        return reports;

    }

    @Override
    public void deleteReport(Long reportId, EntityManager em) {
        CreditBureauReport r=em.find(CreditBureauReport.class, reportId);
        em.remove(r);
    }

    @Override
    public List<CreditBureauReport> getByValue(String value, EntityManager em) {
        String searchingParameter="%"+value+"%";

       return em.createQuery("select m from CreditBureauReport m where m.bankName LIKE :value or m.reportNum LIKE :value or m.legalEntity.name LIKE :value").setParameter("value", searchingParameter)
                .getResultList();

    }

    @Override
    public CreditBureauReport getReportById(Long id, EntityManager em) {
        return em.find(CreditBureauReport.class, id);
    }

    @Override
    public void update(CreditBureauReport report, EntityManager em) {
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

    }

    @Override
    public CreditBureauReport findByReportNumber(String reportNum, EntityManager em) {
        List<CreditBureauReport> dbReports=em.createQuery("select m from CreditBureauReport m where m.reportNum= :value").setParameter("value", reportNum)
                .getResultList();
        return  dbReports.isEmpty() ? null : dbReports.get(0);


    }

    @Override
    public void deleteByEntityId(Long id, EntityManager em) {
       List<CreditBureauReport> entityReports=em.createQuery("SELECT m FROM CreditBureauReport m WHERE m.legalEntity.id= :id").setParameter("id", id).getResultList();
       if(entityReports!=null && !entityReports.isEmpty())
       for(int j=0;j<entityReports.size(); j++){
           em.remove(entityReports.get(j));
       }

    }
}
