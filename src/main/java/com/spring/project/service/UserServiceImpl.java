package com.spring.project.service;

import com.spring.project.controller.UserController;
import com.spring.project.dto.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Andrii Barsuk
 */
@Service
@Log4j2
@Component
public class UserServiceImpl implements UserService {
    @Resource
    private UserController userController;

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    @Override
    public UserDto findUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto signup(UserDto userDto) {
        return null;
    }

    /**
     * Update User Profile
     *
     * @param userDto
     * @return
     */
    @Override
    public UserDto updateProfile(UserDto userDto) {
        return null;
    }

    /**
     * Change Password
     *
     * @param userDto
     * @param newPassword
     * @return
     */
    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        return null;
    }
}
