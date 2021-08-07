package com.spring.project.service;

import com.spring.project.dto.RegistrationDto;
import com.spring.project.model.User;

import java.util.Optional;

/**
 * @author Andrii Barsuk
 */
public interface UserService {
    Optional<User> findUserByEmail(String email);

    User createAccount(RegistrationDto registrationDto); //todo: Optional?

//    UserDto changePassword(UserDto userDto, String newPassword); //todo: Optional?

//    UserDto updateProfile(UserDto userDto); //todo: Optional?

//    boolean promote(String email, Role role);
}
