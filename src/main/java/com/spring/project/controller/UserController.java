package com.spring.project.controller;

import com.spring.project.dto.UserSignUpDto;
import com.spring.project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Andrii Barsuk
 */
@Controller
public class UserController {
    private UserService userService;

    @GetMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("user", new UserSignUpDto());
        return modelAndView;
    }

}
