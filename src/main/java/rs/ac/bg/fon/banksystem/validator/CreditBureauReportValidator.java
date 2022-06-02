package rs.ac.bg.fon.banksystem.validator;

import org.springframework.validation.Validator;
import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.model.LegalEntity;

import javax.persistence.EntityManager;

public interface CreditBureauReportValidator {
    void validate(CreditBureauReport report, EntityManager em) throws ValidationException;

}
