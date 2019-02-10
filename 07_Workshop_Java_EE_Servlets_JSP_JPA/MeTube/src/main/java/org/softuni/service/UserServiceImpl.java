package org.softuni.service;

import org.softuni.domain.dto.LoggedInUserInfoDto;
import org.softuni.domain.dto.LoginUserDto;
import org.softuni.domain.dto.RegisterUserDto;
import org.softuni.domain.model.User;
import org.softuni.repositories.UserRepository;
import org.softuni.util.validators.UserValidator;

import javax.inject.Inject;


public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Inject
    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public boolean registerUser(RegisterUserDto userDto){
        if(!this.userValidator.validate(userDto)){
            return false;
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        this.userRepository.save(user);
        return true;
    }

    @Override
    public LoggedInUserInfoDto loginUser(LoginUserDto userDto){
        User user = this.userRepository.getByUsername(userDto.getUsername());
        if(user == null){
            return null;
        }
        return new LoggedInUserInfoDto(user.getUsername(), user.getEmail());
    }
}
