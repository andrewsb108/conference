package com.spring.project.service;

import com.spring.project.dto.*;
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

    void deleteById(long id);

    User findUserById(String speaker);
}
