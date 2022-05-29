package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.Township;

import java.util.List;


public interface TownshipRepository{
    Township findByZipCode(Long zipCode);
    void delete(Township township);
    List<Township> findByPlaceId(Long placeId);
}
