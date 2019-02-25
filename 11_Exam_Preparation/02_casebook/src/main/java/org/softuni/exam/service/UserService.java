package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel getUserByUsername(String username);

    void createUser(UserServiceModel userServiceModel);

    List<UserServiceModel> getUsersList(String id);

    void makeFriends(String id1, String id2);
}
