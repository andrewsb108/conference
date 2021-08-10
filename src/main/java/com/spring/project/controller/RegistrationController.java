package com.spring.project.controller;

import com.spring.project.dto.RegistrationDto;
import com.spring.project.model.User;
import com.spring.project.service.UserService;
import com.spring.project.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class RegistrationController {
    @Resource
    private final UserService userService;

    @Resource
    private PasswordValidator passwordValidator;

    @GetMapping
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("registrationDto", new RegistrationDto());
        return modelAndView;
    }


    @PostMapping
    public String createNewUserAccount(@ModelAttribute("registrationDto") @Valid RegistrationDto registrationDto,
                                       BindingResult bindingResult) {
        passwordValidator.validate(registrationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        try {
            User newUser = userService.createAccount(registrationDto);
            log.info("Account ({}) registered successfully", newUser.getEmail());
        } catch (Exception e) {
            bindingResult.rejectValue("email", "user.email", "reg.login.not.unique");
            return "signup";
        }
        return "redirect:login";
    }
}
