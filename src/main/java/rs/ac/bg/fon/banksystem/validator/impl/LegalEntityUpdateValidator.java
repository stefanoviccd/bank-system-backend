package rs.ac.bg.fon.banksystem.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.repository.impl.LegalEntityRepositoryImpl;
import rs.ac.bg.fon.banksystem.validator.LegalEntityValidator;

import javax.persistence.EntityManager;
@Component
public class LegalEntityUpdateValidator implements LegalEntityValidator {
    @Autowired
    private LegalEntityRepositoryImpl repository;
    @Override
    public void validate(LegalEntity entity, EntityManager em) throws ValidationException {
        if (entity == null) {
            throw new ValidationException("Pravno lice za izmenu je null!");
        }

        if(entity.getId()==null){
            throw  new ValidationException("Pravno lice za izmenu nema id!");
        }
        LegalEntity dbEntity=repository.getById(entity.getId(), em);
        if(dbEntity==null){
            throw new ValidationException("Pravno lice za izmenu poseduje id koji ne postoji u bazi!");
        }
        dbEntity=repository.findByAccountNumber(entity.getAccountNumber(), em);
        if(dbEntity!=null && dbEntity.getId()!=entity.getId()){
            throw new ValidationException("Pravno lice sa ovim brojem računa postoji u bazi.");
        }
        dbEntity=repository.findByIdentification(entity.getIdentificationNumber(), em);
        if(dbEntity!=null && dbEntity.getId()!=entity.getId()){
            throw new ValidationException("Pravno lice sa ovim matičnim brojem postoji u bazi.");
        }


    }
}
