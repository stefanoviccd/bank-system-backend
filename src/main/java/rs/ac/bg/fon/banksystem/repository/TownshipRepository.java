package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.Township;

import javax.persistence.EntityManager;
import java.util.List;


public interface TownshipRepository{
    Township findByZipCode(Long zipCode, EntityManager em);
    void delete(Township township, EntityManager em);
    List<Township> findByPlaceId(Long placeId, EntityManager em);
}
