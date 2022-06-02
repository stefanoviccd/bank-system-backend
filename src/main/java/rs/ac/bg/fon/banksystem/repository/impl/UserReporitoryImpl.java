package rs.ac.bg.fon.banksystem.repository.impl;

import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.banksystem.dbConnection.EntityManagerProvider;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.model.User;
import rs.ac.bg.fon.banksystem.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserReporitoryImpl implements UserRepository {

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        EntityManager em= EntityManagerProvider.getInstance().getEntityManager();
        List<User> users = em.createQuery("select m from User m where m.username LIKE :u and  m.password LIKE :p").setParameter("u", username)
                .setParameter("p",password)
                .getResultList();
        if(users.isEmpty()) return null;

        return users.get(0);
    }

    @Override
    public User findByUsername(String username) {
        EntityManager em= EntityManagerProvider.getInstance().getEntityManager();
        List<User> users = em.createQuery("select m from User m where m.username LIKE :u").setParameter("u", username)
                .getResultList();
        if(users.isEmpty()) return null;

        return users.get(0);
    }
}
