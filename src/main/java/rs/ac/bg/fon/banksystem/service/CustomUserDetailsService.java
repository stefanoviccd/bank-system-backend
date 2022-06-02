package rs.ac.bg.fon.banksystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityManager em= EntityManagerProvider.getInstance().getEntityManager();
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
        try {
            rs.ac.bg.fon.banksystem.model.User u=userRepository.findByUsername(username);
            if(u==null) throw new Exception("Neispravno korisničko ime ili lozinka.");
            em.getTransaction().commit();
            return new User(u.getUsername(), u.getPassword(), new ArrayList<>());
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);

        }
        finally {
            em.close();
        }

    }
}
