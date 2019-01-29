package services;

import data.User;
import enums.UserRole;
import repositories.UserRepository;
import utils.ApplicationUtils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "userService")
public class UserService {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = ApplicationUtils.getRepository("userRepo");

    }

    public void register(User user){
        if(this.userRepository.getAll().isEmpty()){
            user.setUserRole(UserRole.ADMIN);
        }
        this.userRepository.add(user);
    }

    public boolean login(String username, String password){
        User user = this.userRepository.get(username);
        if(user!= null && user.getPassword().equals(password)){
            if(user.getUserRole().equals(UserRole.ADMIN)){
                ApplicationUtils.getSession().setAttribute("isAdmin", true);
            }
            ApplicationUtils.getSession().setAttribute("username", user.getUsername());
            return true;
        }
        return false;
    }

    public String logOut(){
        if(ApplicationUtils.getSession().getAttribute("username") != null){
            ApplicationUtils.getSession().invalidate();
        }

        return "/index?faces-redirect=true";

    }
    public User getCurrentUser(){
        return null;
    }
}
