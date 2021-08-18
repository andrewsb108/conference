package com.spring.project.controller;

import com.spring.project.dto.RegistrationDto;
import com.spring.project.exceptions.UserAlreadyExistException;
import com.spring.project.service.UserService;
import com.spring.project.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class RegistrationController {

    private final UserService userService;
    private final PasswordValidator passwordValidator;
    private final MessageSource messageSource;

    @GetMapping
    public String signup(@ModelAttribute("user")  RegistrationDto regDto) {
        return "signup";
    }

    @PostMapping
    public String createNewUserAccount(@ModelAttribute("user") @Valid RegistrationDto registrationDto,
                                       BindingResult bindingResult, Model model) {
        passwordValidator.validate(registrationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        try {
            userService.createUser(registrationDto);
            model.addAttribute("message", messageSource.getMessage("reg.success",
                    null, LocaleContextHolder.getLocale()));
        } catch (UserAlreadyExistException e) {
            model.addAttribute("error_message", messageSource.getMessage("reg.login.not.unique",
                    null, LocaleContextHolder.getLocale()) + registrationDto.getEmail());
            return "signup";
        }
        return "redirect:login";
    }
}
