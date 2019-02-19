package opg.softuni.panda.services;

import opg.softuni.panda.domain.dto.UserListBindingModel;
import opg.softuni.panda.domain.dto.UserLoginBindingModel;
import opg.softuni.panda.domain.dto.UserRegisterBindingModel;

import java.util.List;

public interface UserService {
    boolean register(UserRegisterBindingModel model);

    boolean login(UserLoginBindingModel model);

    List<UserListBindingModel> listAllUsers();
}
