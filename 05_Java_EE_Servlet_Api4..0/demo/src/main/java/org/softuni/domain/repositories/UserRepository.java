package org.softuni.domain.repositories;

import org.softuni.domain.model.User;

import javax.persistence.NoResultException;
import java.util.List;


public class UserRepository extends BaseRepository {
    public UserRepository() {

    }

    public User findById(String id) {
        User result = (User) executeAction(repositoryActionResult -> {
            User userFromDb = null;
            try{
                userFromDb = (User) this.entityManager.createNativeQuery(
                        "SELECT * FROM users WHERE id ='" + id + "'", User.class)
                        .getSingleResult();

            }catch (NoResultException e){
                ;
            }
            repositoryActionResult.setResult(userFromDb);

        }).getResult();

        return result;
    }

    public User findByUsername(String username) {
        User result = (User) executeAction(repositoryActionResult -> {
            User userFromDb = null;
            try{
                userFromDb = (User) this.entityManager.createNativeQuery(
                        "SELECT * FROM users WHERE name ='" + username + "'", User.class)
                        .getSingleResult();

            }catch (NoResultException e){
                ;
            }
            repositoryActionResult.setResult(userFromDb);

        }).getResult();

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> result = (List<User>) executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    this.entityManager.createNativeQuery(
                            "SELECT * FROM users", User.class)
                            .getResultList());
        }).getResult();

        return result;
    }

    public void saveUser(User user) {
        executeAction(repositoryActionResult -> {
            this.entityManager.persist(user);
        });
    }

    public void deleteUser(String id){
        executeAction(repositoryActionResult -> {
            this.entityManager.createQuery("delete from User u where u.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        });

    }



}
