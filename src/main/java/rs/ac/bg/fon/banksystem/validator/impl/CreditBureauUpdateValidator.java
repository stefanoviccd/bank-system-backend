package rs.ac.bg.fon.banksystem.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.repository.CreditBureauReportRepository;
import rs.ac.bg.fon.banksystem.repository.impl.CreditBureauReportRepositoryImpl;
import rs.ac.bg.fon.banksystem.validator.CreditBureauReportValidator;

import javax.persistence.EntityManager;
@Component
public class CreditBureauUpdateValidator implements CreditBureauReportValidator {
    @Autowired
    private CreditBureauReportRepository repository;
    public CreditBureauUpdateValidator(){

    }
    @Override
    public void validate(CreditBureauReport report, EntityManager em) throws ValidationException {
        if (report == null) {
            throw new ValidationException("Izveštaj za izmenu je null!");
        }
        CreditBureauReport dbReport=repository.findByReportNumber(report.getReportNum(), em);
        if(dbReport!=null && dbReport.getId()!=report.getId()){
            throw new ValidationException("Izveštaj sa navedenim brojem već postoji.");
        }

    }
}
