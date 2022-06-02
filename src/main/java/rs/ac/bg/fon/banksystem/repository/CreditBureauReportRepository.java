package rs.ac.bg.fon.banksystem.repository;

import rs.ac.bg.fon.banksystem.model.CreditBureauReport;

import javax.persistence.EntityManager;
import java.util.List;

public interface CreditBureauReportRepository {
    void addCreditBureauReport(CreditBureauReport report, EntityManager em);
    List<CreditBureauReport> getAllReports(EntityManager em);

    void deleteReport(Long reportId, EntityManager em);

    List<CreditBureauReport> getByValue(String value, EntityManager em);

    CreditBureauReport getReportById(Long id, EntityManager em);

    void update(CreditBureauReport report, EntityManager em);

    CreditBureauReport findByReportNumber(String reportNum, EntityManager em);

    void deleteByEntityId(Long id, EntityManager em);
}
