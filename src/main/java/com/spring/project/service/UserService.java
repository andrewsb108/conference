package com.spring.project.service;

import com.spring.project.dto.LoginDto;
import com.spring.project.dto.RegistrationDto;
import com.spring.project.dto.UpdateUserDto;
import com.spring.project.model.User;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

/**
 * @author Andrii Barsuk
 */
public interface UserService {
    Optional<User> findUserByEmail(String email);

    User createAccount(RegistrationDto registrationDto);

    User getUser(LoginDto loginDto) throws CredentialException;

    Optional<User> updateProfile(UpdateUserDto updateUserDto);

//    User changePassword(UserDto userDto, String newPassword);

//    boolean promote(String email, Role role);

}
