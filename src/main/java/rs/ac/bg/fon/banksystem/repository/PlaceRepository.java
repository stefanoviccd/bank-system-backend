package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByName(String name);

}
