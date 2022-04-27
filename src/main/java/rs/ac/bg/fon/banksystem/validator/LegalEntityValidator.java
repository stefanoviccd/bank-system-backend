package rs.ac.bg.fon.banksystem.validator;

import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;

public interface LegalEntityValidator {
    void validate(LegalEntity entity) throws ValidationException;
}
