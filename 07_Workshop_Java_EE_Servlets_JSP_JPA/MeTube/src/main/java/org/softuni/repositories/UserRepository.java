package org.softuni.repositories;


import org.softuni.domain.model.User;

import javax.persistence.NoResultException;

public class UserRepository extends BaseRepository {

    public void save(User user){
        this.executeAction(repositoryActionResult -> this.entityManager.persist(user));
    }

    public User getByUsername(String username){
        return (User) this.executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    this.getSingleUserByName(username)
            );
        }).getResult();
    }

    private Object getSingleUserByName(String name) {
        Object result = null;
        try {
            result = this.entityManager.createQuery(
                    "select u from User u where u.username = :name"
            ).setParameter("name", name).getSingleResult();
        }catch (NoResultException e){
            return null;
        }

        return result;
    }

}
