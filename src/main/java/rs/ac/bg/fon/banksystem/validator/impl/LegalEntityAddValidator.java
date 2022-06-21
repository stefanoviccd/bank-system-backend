package rs.ac.bg.fon.banksystem.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.repository.impl.LegalEntityRepositoryImpl;
import rs.ac.bg.fon.banksystem.validator.LegalEntityValidator;

import javax.persistence.EntityManager;
@Component
public class LegalEntityAddValidator implements LegalEntityValidator {
@Autowired
    private LegalEntityRepositoryImpl repository;
    @Override
    public void validate(LegalEntity entity, EntityManager em) throws ValidationException {
        if (entity == null) {
            throw new ValidationException("Pravno lice je null.");
        }
        if (entity.getStreet() == null) {
            throw new ValidationException("Ulica pravnog lica je null.");
        }
        if (entity.getStreet().getTownship() == null) {
            throw new ValidationException("Opština pravnog lica je null.");
        }
        if (entity.getStreet().getTownship().getPlace() == null) {
            throw new ValidationException("Sedište pravnog lica je null.");
        }

        LegalEntity dbEntity=repository.findByAccountNumber(entity.getAccountNumber(), em);
        if(dbEntity!=null){
            throw new ValidationException("Pravno lice sa ovim brojem računa postoji u bazi.");
        }
        dbEntity=repository.findByIdentification(entity.getIdentificationNumber(), em);
        if(dbEntity!=null){
            throw new ValidationException("Pravno lice sa ovim matičnim brojem postoji u bazi.");
        }
    }
}
