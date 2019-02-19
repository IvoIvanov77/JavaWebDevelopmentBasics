package opg.softuni.panda.web.beans;

import opg.softuni.panda.domain.dto.UserLoginBindingModel;
import opg.softuni.panda.services.UserService;
import opg.softuni.panda.util.ApplicationUtils;

import javax.inject.Inject;
import javax.inject.Named;

@Named("loginBean")
public class LoginBean {
    private final UserService userService;

    private UserLoginBindingModel model;

    @Inject
    public LoginBean(UserService userService, UserLoginBindingModel model) {
        this.userService = userService;
        this.model = model;
    }

    public UserLoginBindingModel getModel() {
        return model;
    }

    public void setModel(UserLoginBindingModel model) {
        this.model = model;
    }

    public void login(){
        if(this.userService.login(this.model)){
            ApplicationUtils.redirect("/");
            System.out.println("success");
        }
    }
}
