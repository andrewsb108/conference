package com.spring.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Andrii Barsuk
 */

@Configuration
public class PageConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("start-page");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/profile").setViewName("profile");

    }
}
