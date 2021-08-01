package com.spring.project.service;

import com.spring.project.dto.UserDto;

/**
 * @author Andrii Barsuk
 */
public interface UserService {
   UserDto findUserByEmail(String email);
}
