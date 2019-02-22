package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {
    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User findById(String id) {
        return execute((em) ->
                em.createQuery("select e from User e where e.id = :id", User.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    @Override
    public User findByUsername(String username){
        return execute((em) ->
                em.createQuery("select u from User u where u.username = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult());
    }

    @Override
    public List<User> findAll() {
        return execute(entityManager ->
                entityManager.createQuery("SELECT e FROM User e", User.class)
                        .getResultList());
    }

    @Override
    public User save(User user) {
        return execute(entityManager ->{
            User e = entityManager.merge(user);
            entityManager.flush();
            return e;
        });

    }

    @Override
    public User delete(User user) {
        return execute(entityManager ->{
            entityManager.remove(user);
            entityManager.flush();
            return user;
        });
    }

    @Override
    public Long size() {
        return execute(entityManager ->
                entityManager
                        .createQuery("SELECT count(u) FROM User u", Long.class)
                        .getSingleResult());
    }


//    @Override
//    public User save(User user) {
//        return this.executeTransaction((em) -> {
//            em.persist(user);
//            return user;
//        });
//    }

}
