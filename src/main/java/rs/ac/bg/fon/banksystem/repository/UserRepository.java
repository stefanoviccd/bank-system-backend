package rs.ac.bg.fon.banksystem.repository;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.User;

import javax.persistence.EntityManager;

@Repository
public interface UserRepository {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

}
