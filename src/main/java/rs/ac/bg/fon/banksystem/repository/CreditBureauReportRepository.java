package rs.ac.bg.fon.banksystem.repository;

import rs.ac.bg.fon.banksystem.model.CreditBureauReport;

import java.util.List;

public interface CreditBureauReportRepository {
    void addCreditBureauReport(CreditBureauReport report);
    List<CreditBureauReport> getAllReports();

    void deleteReport(Long reportId);

    List<CreditBureauReport> getByValue(String value);

    CreditBureauReport getReportById(Long id);

    void update(CreditBureauReport report);

    CreditBureauReport findByReportNumber(String reportNum);

    void deleteByEntityId(Long id);
}
