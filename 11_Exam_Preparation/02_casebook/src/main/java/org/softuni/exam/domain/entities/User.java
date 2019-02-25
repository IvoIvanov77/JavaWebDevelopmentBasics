package org.softuni.exam.domain.entities;

import org.softuni.exam.domain.enums.Gender;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String username;

    private String password;

    private Gender gender;

    private Set<User> friends;

    public User() {
        this.friends = new HashSet<>();
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_friend",  joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "friend_id",
                    nullable = false, updatable = false) })
    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

//    public boolean addFriend(User friend){
//
//        return this.friends.add(friend);
//    }
//
//    public boolean removeFriend(User friend){
//        return this.friends.remove(friend);
//    }
}
