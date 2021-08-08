package com.spring.project.service;

import com.spring.project.dto.LoginDto;
import com.spring.project.dto.RegistrationDto;
import com.spring.project.exceptions.UserAlreadyExistException;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.User;
import com.spring.project.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.security.auth.login.CredentialException;
import java.util.*;

/**
 * @author Andrii Barsuk
 */
@Service
@Log4j2
@Component
public class UserServiceImpl implements UserService {
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BusinessMapper businessMapper;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User createAccount(RegistrationDto registrationDto) throws UserAlreadyExistException {
        User user = businessMapper.convertFromRegistrationDtoToUser(registrationDto);
        if (emailExist(user.getEmail())) {
            log.warn("Trying to register a new account {}: " +
                    "There is an account with this email address", user.getEmail());
            throw new UserAlreadyExistException("reg.login.not.unique");
        }
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).orElse(null) != null;
    }

    public User getUser(LoginDto loginDto) throws CredentialException {
        User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
                .orElseThrow(() -> new CredentialException("valid.login.wrong.credential"));
        log.info("User {} successfully logged in", loginDto.getEmail());
        return user;
    }

//    public User getUserById(Long id) {
//        return businessMapper.convertUserToUserDto(userRepository.findById(id).orElseThrow(() ->
//                new UsernameNotFoundException("No such user was found, id: " + id)));
//    }

//    @Override
//    public User changePassword(UserDto userDto, String newPassword) {
//        return null;
//    }
//
//    @Override
//    public User updateProfile(UserDto userDto) {
//        return null;
//    }
//
//    public void deleteById(long id) {
//        try {
//            userRepository.deleteById(id);
//        } catch (NoSuchElementException e) {
//            log.info("Deleted category with id: " + id);
//        }
//    }


//    public List<UserDto> getAllUsers() {
//        return businessMapper.convertUserToUserDto(userRepository.findAll());
//    }

//    @Override
//    public UserDto changePassword(UserDto userDto, String newPassword) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
//        if (user.isPresent()) {
//            User userModel = user.get();
//            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
//            return BusinessMapper.convertUserToUserDto(userRepository.save(userModel));
//        }
//        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
//    }
//
//    public User getUser(UserDto userDto) {
//        return null;
//    }
//
//    @Override
//    public UserDto updateProfile(UserDto userDto) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
//        if (user.isPresent()) {
//            User userModel = user.get();
//            userModel.setFirstName(userDto.getFirstName());
//            userModel.setLastName(userDto.getLastName());
//
//            return businessMapper.convertUserToUserDto(userRepository.save(userModel));
//        }
//        throw new UsernameNotFoundException("User not found");
//    }
}
