package opg.softuni.panda.repositories;

import opg.softuni.panda.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends GenericRepository implements UserRepository {

    @Inject
    public UserRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public User findById(String id) {
        return (User) execute((em) ->
                em.createQuery("select e from User e where e.id = :id", User.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    @Override
    public User findByUsername(String username){
        return (User) execute((em) ->
                em.createQuery("select u from User u where u.username = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return (List<User>) execute(entityManager ->
                entityManager.createQuery("select e from User e", User.class)
                        .getResultList());
    }

    @Override
    public User save(User user) {
        return (User) execute(entityManager ->{
            User e = entityManager.merge(user);
            entityManager.flush();
            return e;
        });

    }

    @Override
    public User delete(User user) {
        return (User) execute(entityManager ->{
            entityManager.remove(user);
            entityManager.flush();
            return user;
        });
    }

    @Override
    public Long size() {
        return (Long) execute(entityManager ->
                entityManager
                        .createQuery("SELECT count(u) FROM User u", Long.class)
                        .getSingleResult());
    }
}
