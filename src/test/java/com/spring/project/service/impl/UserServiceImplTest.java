package com.spring.project.service.impl;

import com.spring.project.dto.RegistrationDto;
import com.spring.project.model.User;
import com.spring.project.model.enums.Role;
import com.spring.project.repository.UserRepository;
import com.spring.project.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "user@mail.com";
    private final String password = "1234";
    private final Role role = Role.USER;
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void createUser() {
        RegistrationDto dto = new RegistrationDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setPassword(password);

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(email)
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Collections.singleton(role))
                .build();

        userService.createUser(dto);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(passwordEncoder, Mockito.times(2)).encode(dto.getPassword());

    }

    @Test
    public void removeUser() {
        User user = User.builder()
                .id(1l)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singleton(role))
                .build();

        Mockito.doReturn(user)
                .when(userRepository)
                .findById(1l);

        userService.deleteById(user.getId());

        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
    }
}