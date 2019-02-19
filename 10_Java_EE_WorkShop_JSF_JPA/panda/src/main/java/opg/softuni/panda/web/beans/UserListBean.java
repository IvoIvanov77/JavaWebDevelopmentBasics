package opg.softuni.panda.web.beans;

import opg.softuni.panda.domain.dto.UserListBindingModel;
import opg.softuni.panda.services.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "userList")
public class UserListBean {
    private final UserService userService;


    @Inject
    public UserListBean(UserService userService) {
        this.userService = userService;
    }

    public List<UserListBindingModel> getAllUsers() {
        return this.userService.listAllUsers();
    }
}
