package com.spring.project.service;

import com.spring.project.dto.UserDto;

/**
 * @author Andrii Barsuk
 */
public interface UserService {
   /**
    * Search an existing user
    *
    * @param email
    * @return
    */
   UserDto findUserByEmail(String email);
   /**
    * Register a new user
    *
    * @param userDto
    * @return
    */
   UserDto signup(UserDto userDto);
}
