package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.banksystem.model.LegalEntity;

public interface LegalEntityRepository  extends JpaRepository<LegalEntity, Long> {

    LegalEntity findByAccountNumber(String accountNumber);
}
