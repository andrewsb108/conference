package com.spring.project.service;

import com.spring.project.dto.LoginDto;
import com.spring.project.dto.RegistrationDto;
import com.spring.project.dto.UpdateUserDto;
import com.spring.project.dto.UserDto;
import com.spring.project.exceptions.EmailNotUniqueException;
import com.spring.project.exceptions.UserAlreadyExistException;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.User;
import com.spring.project.model.enums.UserRole;
import com.spring.project.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.security.auth.login.CredentialException;
import java.util.Collections;
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

    @Resource
    private MessageSource messageSource;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(RegistrationDto registrationDto) throws UserAlreadyExistException {
        try {
            User user = businessMapper.convertRegistrationDtoToUser(registrationDto);
            log.info("Handling save users: " + registrationDto);
            user.setRoles(Collections.singleton(UserRole.USER));
            user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
            return userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            log.warn("Login not unique: " + registrationDto.getEmail());
            throw new UserAlreadyExistException(messageSource.getMessage(
                    "reg.login.not.unique", null,
                    LocaleContextHolder.getLocale()) + registrationDto.getEmail(), e);
        }
    }

    @Override
    public User getUser(LoginDto loginDto) throws CredentialException {
        User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
                .orElseThrow(() -> new CredentialException("valid.login.wrong.credential"));
        log.info("User {} successfully logged in", loginDto.getEmail());

        return user;
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Handling find all users request");
        return businessMapper.convertUserToUserDtoGetAll(userRepository.findAll());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("No such user was found, id: " + id));
    }

    @Override
    public Optional<User> updateProfile(UpdateUserDto updateUserDto) {
        User user = businessMapper.convertFromUpdateUserDtoToUser(updateUserDto);
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            log.info("Deleted category with id: " + id);
        }
    }
}
