package com.spring.project.mapping;

import com.spring.project.dto.RegistrationDto;
import com.spring.project.dto.RoleDto;
import com.spring.project.dto.UserDto;
import com.spring.project.model.Role;
import com.spring.project.model.User;
import com.spring.project.model.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class BusinessMapper {
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    public UserDto convertUserToUserDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setEmail(user.getEmail());
//        userDto.setFirstName(user.getFirstName());
//        userDto.setLastName(user.getLastName());
//        userDto.setPassword(bCryptPasswordEncoder.encode((user.getPassword())));
//
//        return userDto;
//    }

    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(bCryptPasswordEncoder.encode((user.getPassword())));

        return userDto;
    }

    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(UserRole.USER));

        return user;
    }

    public UserDto convertFromSignupDtoToUserDto(RegistrationDto registrationDto) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(registrationDto.getFirstName());
        userDto.setLastName(registrationDto.getLastName());
        userDto.setEmail(registrationDto.getEmail());
        userDto.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));

        return userDto;
    }

    public User convertFromRegistrationDtoToUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        user.setCreated(LocalDateTime.now());

        return user;
    }

    public RoleDto convertToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());

        return roleDto;
    }


}
