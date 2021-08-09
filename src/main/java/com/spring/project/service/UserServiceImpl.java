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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Component
@Service
public class UserServiceImpl implements UserService {

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

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user ->
                businessMapper.convertUserToUserDto(user)).collect(Collectors.toList());
//        return businessMapper.convertUserToUserDto(userRepository.findAll());
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("No such user was found, id: " + id));
    }

    public User updateProfile(UpdateUserDto updateUserDto) {
        User user = userRepository.findById(updateUserDto.getId()).orElseThrow(() ->
                new UsernameNotFoundException("No such user found"));
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());

        return userRepository.save(user);
    }

    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            log.info("Deleted category with id: " + id);
        }
    }

//    @Override
//    public User changePassword(UserDto userDto, String newPassword) {
//        return null;
//    }


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
