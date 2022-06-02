package rs.ac.bg.fon.banksystem.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.User;
import rs.ac.bg.fon.banksystem.repository.UserRepository;


import javax.persistence.EntityManager;
import java.util.ArrayList;
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserRepository repository;

    public CustomAuthenticationManager(){

    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        EntityManager em= EntityManagerProvider.getInstance().getEntityManager();
        try {

            em.getTransaction().begin();
            User u=repository.findByUsernameAndPassword(name, password);
            em.getTransaction().commit();
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        finally{
            em.close();
            EntityManagerProvider.getInstance().closeSession();
        }
    }
}
