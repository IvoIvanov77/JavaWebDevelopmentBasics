package actions;

import data.User;
import services.UserService;
import utils.ApplicationUtils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "registerAction")
public class RegisterAction {

    private String username;

    private String password;

    private String confirmPassword;

    private String errorMessage;

    private UserService userService;

    public RegisterAction() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void register(){
        if(!this.confirmPassword.equals(this.password)){
            this.setErrorMessage("Passwords do not match");
            return;
        }
        this.userService.register(new User(this.username, this.password));
        ApplicationUtils.redirect("/users/login.xhtml");
    }
}
