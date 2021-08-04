package com.spring.project.config;

import com.spring.project.mapping.BusinessMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Andrii Barsuk
 */
@Configuration
public class ProjectConfiguration {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
    @Bean
    public BusinessMapper createBusinessMapper(){
        return new BusinessMapper();
    }
}
