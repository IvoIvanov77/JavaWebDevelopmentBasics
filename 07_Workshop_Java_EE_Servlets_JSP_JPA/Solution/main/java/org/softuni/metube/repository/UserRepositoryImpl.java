package org.softuni.metube.repository;

import org.softuni.metube.domain.User;
import org.softuni.metube.domain.UserRole;

import java.util.List;

public class UserRepositoryImpl extends GenericRepository implements UserRepository {
    @Override
    public User findByUsername(String username) {
        return (User) execute((em) -> em
                .createNativeQuery("SELECT * FROM users WHERE username = \'" + username + "\'",
                        User.class).getResultList().get(0));
    }

    @Override
    public User findById(String id) {
        return (User) execute((em) -> em.createNativeQuery("SELECT * FROM users WHERE id = \'" + id + "\'", User.class).getSingleResult());
    }

    @Override
    public List<User> findAll() {
        return (List<User>) execute((em) -> em.createNativeQuery("SELECT * FROM users", User.class)
                .getResultList());
    }

    @Override
    public User save(User user) {
        return (User) execute((em) -> {
            em.persist(user);
            em.flush();
            return user;
        });
    }

    @Override
    public User delete(User user) {
        return (User) execute((em) -> {
            em.remove(user);
            em.flush();
            return user;
        });
    }
}
