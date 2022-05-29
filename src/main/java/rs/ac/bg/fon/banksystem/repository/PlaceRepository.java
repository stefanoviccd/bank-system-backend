package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.Place;


public interface PlaceRepository{
    Place findByName(String name);
    public void delete(Place place);

}
