package rs.ac.bg.fon.banksystem.validator.impl;

import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.repository.CreditBureauReportRepository;
import rs.ac.bg.fon.banksystem.repository.CreditBureauReportRepositoryImpl;
import rs.ac.bg.fon.banksystem.validator.CreditBureauReportValidator;

public class CreditBureauAddValidator implements CreditBureauReportValidator {
    private CreditBureauReportRepository repository;
    public CreditBureauAddValidator(){
        repository=new CreditBureauReportRepositoryImpl();
    }
    @Override
    public void validate(CreditBureauReport report) throws ValidationException {
        CreditBureauReport dbReport=repository.findByReportNumber(report.getReportNum());
        if(dbReport!=null){
            throw new ValidationException("Credit bureau report with this report number exists.");
        }


    }
}
