package com.spring.project.service;

import com.spring.project.dto.LoginDto;
import com.spring.project.dto.RegistrationDto;
import com.spring.project.dto.UpdateUserDto;
import com.spring.project.dto.UserDto;
import com.spring.project.exceptions.UserAlreadyExistException;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.User;
import com.spring.project.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.security.auth.login.CredentialException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private BusinessMapper businessMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createAccount(RegistrationDto registrationDto) throws UserAlreadyExistException {
        User user = businessMapper.convertRegistrationDtoToUser(registrationDto);
        log.info("Handling save users: " + registrationDto);
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(LoginDto loginDto) throws CredentialException {
        User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
                .orElseThrow(() -> new CredentialException("valid.login.wrong.credential"));
        log.info("User {} successfully logged in", loginDto.getEmail());

        return user;
    }

    public List<UserDto> getAllUsers() {
        log.info("Handling find all users request");
        return businessMapper.convertUserToUserDtoGetAll(userRepository.findAll());
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("No such user was found, id: " + id));
    }

    public Optional<User> updateProfile(UpdateUserDto updateUserDto) {
        User user = businessMapper.convertFromUpdateUserDtoToUser(updateUserDto);
        return Optional.ofNullable(userRepository.save(user));
    }


    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            log.info("Deleted category with id: " + id);
        }
    }

}
