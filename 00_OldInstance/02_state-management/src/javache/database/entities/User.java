package javache.database.entities;

import java.util.UUID;

public class User implements Entity{
    private String id;

    private String email;

    private String password;

    public User(String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
    }

    private User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User createUser(String line, String separator){
        String[] userData = line.split(separator);
        if(userData.length != 3){
            throw new IllegalArgumentException("Invalid line");
        }
        return new User(userData[0], userData[1], userData[2]);
    }

    @Override
    public String toString() {
        return this.id + "," + this.email + "," + this.password;
    }
}
