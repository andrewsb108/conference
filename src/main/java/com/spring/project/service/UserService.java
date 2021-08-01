package com.spring.project.service;

import com.spring.project.dto.UserDto;

public interface UserService {
   UserDto findUserByEmail(String email);
}
