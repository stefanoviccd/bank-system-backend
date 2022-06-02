package rs.ac.bg.fon.banksystem.validator.impl;

import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.repository.CreditBureauReportRepository;
import rs.ac.bg.fon.banksystem.repository.impl.CreditBureauReportRepositoryImpl;
import rs.ac.bg.fon.banksystem.validator.CreditBureauReportValidator;

import javax.persistence.EntityManager;

public class CreditBureauAddValidator implements CreditBureauReportValidator {
    private CreditBureauReportRepository repository;
    public CreditBureauAddValidator(){
        repository=new CreditBureauReportRepositoryImpl();
    }
    @Override
    public void validate(CreditBureauReport report, EntityManager em) throws ValidationException {
        if (report == null) {
            throw new ValidationException("Report to be saved is null!!");
        }
        if (report.getReportNum() == null) {
            throw new ValidationException("Report does not have report number!");
        }
        CreditBureauReport dbReport=repository.findByReportNumber(report.getReportNum(), em);
        if(dbReport!=null){
            throw new ValidationException("Credit bureau report with this report number exists.");
        }


    }
}
