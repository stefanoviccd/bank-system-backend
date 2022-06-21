package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.Place;

import javax.persistence.EntityManager;


public interface PlaceRepository{
    Place findByName(String name, EntityManager em);
    public void delete(Long id, EntityManager em);

}
