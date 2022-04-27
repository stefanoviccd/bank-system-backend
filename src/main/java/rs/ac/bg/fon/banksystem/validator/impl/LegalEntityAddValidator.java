package rs.ac.bg.fon.banksystem.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.repository.LegalEntityRepository;
import rs.ac.bg.fon.banksystem.repository.LegalEntityRepositoryImpl;
import rs.ac.bg.fon.banksystem.validator.LegalEntityValidator;
public class LegalEntityAddValidator implements LegalEntityValidator {

    private LegalEntityRepositoryImpl repository=new LegalEntityRepositoryImpl();
    @Override
    public void validate(LegalEntity entity) throws ValidationException {
        LegalEntity dbEntity=repository.findByAccountNumber(entity.getAccountNumber());
        if(dbEntity!=null){
            throw new ValidationException("Legal entity with this account number exists.");
        }
        dbEntity=repository.findByIdentification(entity.getIdentificationNumber());
        if(dbEntity!=null){
            throw new ValidationException("Legal entity with this identification number exists.");
        }
    }
}
