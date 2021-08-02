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
    * @return UserDto
    */
   UserDto findUserByEmail(String email);
   /**
    * Register a new user
    *
    * @param userDto
    * @return UserDto
    */
   UserDto signup(UserDto userDto);

   /**
    * Update profile of the user
    *
    * @param userDto
    * @return UserDto
    */
   UserDto updateProfile(UserDto userDto);

   /**
    * Update password
    *
    * @param newPassword
    * @return UserDto
    */
   UserDto changePassword(UserDto userDto, String newPassword);
}
