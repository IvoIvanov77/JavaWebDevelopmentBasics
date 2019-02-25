package org.softuni.exam.domain.models.service;

import org.softuni.exam.domain.entities.User;
import org.softuni.exam.domain.enums.Gender;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {
    private String id;

    private String username;

    private String password;

    private Gender gender;

    private Set<UserServiceModel> friends;

    public UserServiceModel() {
        this.friends = new HashSet<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<UserServiceModel> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserServiceModel> friends) {
        this.friends = friends;
    }
}
