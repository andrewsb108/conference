package com.spring.project.controller;

import com.spring.project.dto.UserDto;
import com.spring.project.dto.UserSignUpDto;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private BusinessMapper businessMapper;

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping(value = {"/logout"})
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:login";
    }

    @PostMapping(value = "/signup")
    public ModelAndView createUser(@Valid @ModelAttribute("userSignupDto") UserSignUpDto userSignUpDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("signup");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
//        else {
            try {
                UserDto newUser = businessMapper.convertFromSignupDtoToUserDto(userSignUpDto);
                userService.signup(newUser);
            } catch (Exception exception) {
//                bindingResult.rejectValue("email", "error.user.not.created");
                bindingResult.rejectValue("email", exception.getMessage());
                return modelAndView;
//            }
        }
        return new ModelAndView("login");
    }

    @GetMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("userSignupDto", new UserSignUpDto());
        return modelAndView;
    }
}
