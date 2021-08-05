package com.spring.project.mapping;

import com.spring.project.dto.RoleDto;
import com.spring.project.dto.UserDto;
import com.spring.project.dto.UserSignUpDto;
import com.spring.project.model.Role;
import com.spring.project.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.stream.Collectors;

public class BusinessMapper {
    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(new HashSet<>(user.getRoles().stream().map(this::convertToRoleDto).collect(Collectors.toSet())));
        return userDto;
    }

    public UserDto convertFromSignupDtoToUserDto(UserSignUpDto userSignUpDto) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(userSignUpDto.getFirstName());
        userDto.setLastName(userSignUpDto.getLastName());
        userDto.setEmail(userSignUpDto.getEmail());
        userDto.setPassword(userSignUpDto.getPassword());
        userDto.setCreated(LocalDateTime.now());

        return userDto;
    }

    public RoleDto convertToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());

        return roleDto;
    }


}
