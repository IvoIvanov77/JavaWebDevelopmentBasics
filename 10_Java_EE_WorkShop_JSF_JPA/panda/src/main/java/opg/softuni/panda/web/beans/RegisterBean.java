package opg.softuni.panda.web.beans;

import opg.softuni.panda.domain.dto.UserRegisterBindingModel;
import opg.softuni.panda.services.UserService;
import opg.softuni.panda.util.ApplicationUtils;

import javax.inject.Inject;
import javax.inject.Named;

@Named("registerBean")
public class RegisterBean {

    private final UserRegisterBindingModel userRegisterBindingModel;
    private final UserService userService;

    @Inject
    public RegisterBean(UserRegisterBindingModel userRegisterBindingModel, UserService userService) {
        this.userRegisterBindingModel = userRegisterBindingModel;
        this.userService = userService;
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void register(){
        if(this.userService.register(this.userRegisterBindingModel)){
            ApplicationUtils.redirect("/jsf/guest/login.xhtml");
        }

    }
}
