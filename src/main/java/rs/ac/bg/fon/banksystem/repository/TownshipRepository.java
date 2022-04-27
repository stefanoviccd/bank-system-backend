package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.Township;

@Repository
public interface TownshipRepository extends JpaRepository<Township, Long> {
    Township getByZipCode(Long zipCode);
}
