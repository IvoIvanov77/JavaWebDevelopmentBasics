package org.softuni.service;

import org.softuni.domain.dto.LoggedInUserInfoDto;
import org.softuni.domain.dto.LoginUserDto;
import org.softuni.domain.dto.RegisterUserDto;

public interface UserService {
    boolean registerUser(RegisterUserDto userDto);

    LoggedInUserInfoDto loginUser(LoginUserDto userDto);
}
