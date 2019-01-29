package org.softuni.database.repositories;

import org.softuni.database.entities.User;

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
                       "SELECT * FROM users WHERE username ='" + username + "'", User.class)
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

    public void addFriend(User user, User friend) {
        executeAction(repositoryActionResult -> {
            user.addFriend(friend);
            friend.addFriend(user);

            this.entityManager.merge(user);
            this.entityManager.merge(friend);
        });
    }

    public void removeFriend(User user, User friend) {
        executeAction(repositoryActionResult -> {
            this.entityManager.createNativeQuery(
                    "DELETE FROM users_friends WHERE user_id = '"
                            + user.getId()
                            + "' AND friend_id = '"
                            + friend.getId()
                            + "'").executeUpdate();

            this.entityManager.createNativeQuery(
                    "DELETE FROM users_friends WHERE user_id = '"
                            + friend.getId()
                            + "' AND friend_id = '"
                            + user.getId()
                            + "'").executeUpdate();

            this.entityManager.merge(user);
            this.entityManager.merge(friend);
        });
    }
}
