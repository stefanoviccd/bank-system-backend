package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.banksystem.model.LegalEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface LegalEntityRepository  {
    LegalEntity save(LegalEntity legalEntity, EntityManager em);
    LegalEntity getById(Long id, EntityManager em);
    LegalEntity update(LegalEntity updateEntity, EntityManager em);
    void delete(LegalEntity entity, EntityManager em);
    List<LegalEntity> findAll(EntityManager em);
    LegalEntity findByAccountNumber(String accountNumber, EntityManager em);
    LegalEntity findByIdentification(String identificationNumber, EntityManager em);
    List<LegalEntity> getByValue(String value, EntityManager em);

}
