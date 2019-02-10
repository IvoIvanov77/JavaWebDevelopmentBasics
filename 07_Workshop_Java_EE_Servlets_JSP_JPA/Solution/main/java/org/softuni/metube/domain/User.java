package org.softuni.metube.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.valueextraction.UnwrapByDefault;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    private String id;

    private String username;

    private String password;

    private String email;

    private UserRole role;

    private List<Tube> tubes;

    public User() {
        this.tubes = new ArrayList<>();
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(
            name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false)
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

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRole getRole() {
        return this.role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "uploader")
    public List<Tube> getTubes() {
        return this.tubes;
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }
}
