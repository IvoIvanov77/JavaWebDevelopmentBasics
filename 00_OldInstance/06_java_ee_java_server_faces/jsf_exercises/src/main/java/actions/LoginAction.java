package actions;

import services.UserService;
import utils.ApplicationUtils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "loginAction")
public class LoginAction {

    private String username;

    private String password;

    private String errorMessage;

    private UserService userService;

    public LoginAction() {
        this.userService = new UserService();
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void login(){
        if(this.userService.login(this.username,this.password)){
            ApplicationUtils.redirect("/");
            return;
        }
        this.setErrorMessage("Invalid credentials");
    }


}
