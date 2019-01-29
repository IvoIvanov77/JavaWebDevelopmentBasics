package data;

import enums.UserRole;

public class User {

    private String username;

    private String password;

    private UserRole userRole;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userRole = UserRole.USER;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isAdmin(){
        return this.userRole.equals(UserRole.ADMIN);
    }
}
