package com.spring.project.service.impl;

import com.spring.project.dto.RegistrationDto;
import com.spring.project.dto.UserDto;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.User;
import com.spring.project.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private User user;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private BusinessMapper businessMapper;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl testInstance;

    @Test
    public void shouldCreateUser() {
        //given
        String encodedPassword = "sadfjhlk4565jh6gfdsfjh";
        RegistrationDto registrationDto = RegistrationDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .password("password")
                .matchingPassword("password")
                .build();
        User user = User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .password("password")
                .build();
        given(businessMapper.convertRegistrationDtoToUser(registrationDto)).willReturn(user);
        given(passwordEncoder.encode(registrationDto.getPassword())).willReturn(encodedPassword);
        given(userRepository.save(user)).willReturn(user);

        //when
        User actualUser = testInstance.createUser(registrationDto);

        //then
        assertThat(actualUser).isEqualTo(user);
        assertThat(actualUser.getPassword()).isEqualTo(encodedPassword);

    }

    @Test
    public void shouldReturnAllUsers() {
        UserDto userDto = new UserDto();
        //given
        List<UserDto> expectedUsers = List.of(userDto);
        List<User> users = userRepository.findAll();
        given(businessMapper.convertUserToUserDtoGetAll(users)).willReturn(expectedUsers);

        //when
        List<UserDto> actualUsers = testInstance.getAllUsers();

        //then
        assertThat(actualUsers).isEqualTo(expectedUsers);
    }

    @Test
    public void delete_test() {
        long id = 1;
        testInstance.deleteById(id);
        verify(userRepository).deleteById(id);
    }
}