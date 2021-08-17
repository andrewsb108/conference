package com.spring.project.security;

import com.spring.project.model.User;
import com.spring.project.repository.UserRepository;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Service
public class SecurityService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Autowired
    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentLoggedUser() {
        return Optional.ofNullable((User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal())
                .orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Not found such user: " + email));
    }
}
