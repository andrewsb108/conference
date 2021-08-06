package com.spring.project.service;

import com.spring.project.dto.UserDto;
import com.spring.project.model.User;

import java.util.Optional;

/**
 * @author Andrii Barsuk
 */
public interface UserService {
    Optional<UserDto> findUserByEmail(String email);

    UserDto signup(UserDto userDto); //todo: Optional?

    UserDto changePassword(UserDto userDto, String newPassword); //todo: Optional?

    UserDto updateProfile(UserDto userDto); //todo: Optional?

//    boolean promote(String email, Role role);
}
