package rs.ac.bg.fon.banksystem.validator.impl;

import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.repository.impl.LegalEntityRepositoryImpl;
import rs.ac.bg.fon.banksystem.validator.LegalEntityValidator;

import javax.persistence.EntityManager;

public class LegalEntityAddValidator implements LegalEntityValidator {

    private LegalEntityRepositoryImpl repository=new LegalEntityRepositoryImpl();
    @Override
    public void validate(LegalEntity entity, EntityManager em) throws ValidationException {
        if (entity == null) {
            throw new ValidationException("Entity is null.");
        }
        if (entity.getStreet() == null) {
            throw new ValidationException("Entity street is null.");
        }
        if (entity.getStreet().getTownship() == null) {
            throw new ValidationException("Entity township is null.");
        }
        if (entity.getStreet().getTownship().getPlace() == null) {
            throw new ValidationException("Entity place is null.");
        }

        LegalEntity dbEntity=repository.findByAccountNumber(entity.getAccountNumber(), em);
        if(dbEntity!=null){
            throw new ValidationException("Legal entity with this account number exists.");
        }
        dbEntity=repository.findByIdentification(entity.getIdentificationNumber(), em);
        if(dbEntity!=null){
            throw new ValidationException("Legal entity with this identification number exists.");
        }
    }
}
