package rs.ac.bg.fon.banksystem.validator.impl;

import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.validator.CreditBureauReportValidator;

import javax.persistence.EntityManager;

public class CreditBureauUpdateValidator implements CreditBureauReportValidator {
    @Override
    public void validate(CreditBureauReport report, EntityManager em) throws ValidationException {
        if (report == null) {
            throw new ValidationException("Report to be updated is null!");
        }

    }
}
