package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.banksystem.model.LegalEntity;

import java.util.List;

public interface LegalEntityRepository  {
    LegalEntity save(LegalEntity legalEntity);
    LegalEntity getById(Long id);
    LegalEntity update(LegalEntity updateEntity);
    void delete(LegalEntity entity);
    List<LegalEntity> findAll();
    LegalEntity findByAccountNumber(String accountNumber);
    LegalEntity findByIdentification(String identificationNumber);
    List<LegalEntity> getByValue(String value);

}
