package rs.ac.bg.fon.banksystem.validator.impl;

import rs.ac.bg.fon.banksystem.exception.ValidationException;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.repository.impl.LegalEntityRepositoryImpl;
import rs.ac.bg.fon.banksystem.validator.LegalEntityValidator;

import javax.persistence.EntityManager;

public class LegalEntityUpdateValidator implements LegalEntityValidator {
    private LegalEntityRepositoryImpl repository=new LegalEntityRepositoryImpl();
    @Override
    public void validate(LegalEntity entity, EntityManager em) throws ValidationException {
        if (entity == null) {
            throw new ValidationException("Entity to be updated is null!");
        }

        if(entity.getId()==null){
            throw  new ValidationException("Entity to be updated does not have id!");
        }
        LegalEntity dbEntity=repository.getById(entity.getId(), em);
        if(dbEntity==null){
            throw new ValidationException("Entity to be updated has id but does not exist in database!");
        }


    }
}
