package rs.ac.bg.fon.banksystem.validator;

import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;

import javax.persistence.EntityManager;

public interface LegalEntityValidator {
    void validate(LegalEntity entity, EntityManager em) throws ValidationException;
}
