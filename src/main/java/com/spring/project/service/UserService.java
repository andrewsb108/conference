package com.spring.project.service;

import com.spring.project.dto.LoginDto;
import com.spring.project.dto.RegistrationDto;
import com.spring.project.dto.UpdateUserDto;
import com.spring.project.dto.UserDto;
import com.spring.project.model.User;

import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrii Barsuk
 */
public interface UserService {
    Optional<User> findUserByEmail(String email);

    User createUser(RegistrationDto registrationDto);

    User getUser(LoginDto loginDto) throws CredentialException;

    User getUserById(Long id);

    Optional<User> updateProfile(UpdateUserDto updateUserDto);

    List<UserDto> getAllUsers();

    void deleteById(Long id);

    User findUserById(String speaker);
}
