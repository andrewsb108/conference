package com.spring.project.service;

import com.spring.project.dto.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Barsuk
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Override
    public UserDto findUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto signup(UserDto userDto) {
        return null;
    }
}
