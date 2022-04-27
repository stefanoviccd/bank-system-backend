package rs.ac.bg.fon.banksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

}
