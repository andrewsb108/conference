package com.spring.project.service;

import com.spring.project.dto.UserDto;
import com.spring.project.exceptions.UserDublicateException;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.Role;
import com.spring.project.model.User;
import com.spring.project.model.enums.UserRoles;
import com.spring.project.repository.RoleRepository;
import com.spring.project.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    private RoleRepository roleRepository;
    @Resource
    private BusinessMapper businessMapper;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("No such user was found, id: " + id));
    }

    @Override
    public Optional<UserDto> findUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto signup(UserDto userDto) {

        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            Role userRole = roleRepository.findByRole(UserRoles.SPEAKER.name());
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            return businessMapper.toUserDto(userRepository.save(user));
        }
        throw new UserDublicateException("User already exist: " + userDto.getEmail());
    }

//    public void deleteById(long id) {
//        try {
//            userRepository.deleteById(id);
//        } catch (NoSuchElementException e) {
//            log.info("Deleted category with id: " + id);
//        }
//    }


//    @Override
//    public UserDto updateProfile(UserDto userDto) {
//        return null;
//    }

//    @Override
//    public UserDto changePassword(UserDto userDto, String newPassword) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
//        if (user.isPresent()) {
//            User userModel = user.get();
//            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
//            return BusinessMapper.toUserDto(userRepository.save(userModel));
//        }
//        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
//    }
}
