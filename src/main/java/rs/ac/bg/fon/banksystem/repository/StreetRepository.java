package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.Street;

import javax.persistence.EntityManager;
import java.util.List;


public interface StreetRepository{
    List<Street> findByTownshipId(Long townShipId, EntityManager em);
}
