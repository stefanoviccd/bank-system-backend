package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
    Street findByStreetNameAndStreetNumber(String name, int number);
}
