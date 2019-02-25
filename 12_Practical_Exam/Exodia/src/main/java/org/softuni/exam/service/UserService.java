package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.UserServiceModel;

public interface UserService {
    UserServiceModel getUserByUsername(String username);

    boolean createUser(UserServiceModel userServiceModel);
}
