package com.spring.project.controller;

import com.spring.project.dto.LoginDto;
import com.spring.project.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.security.auth.login.CredentialException;
import javax.validation.Valid;

@Log4j2
@Controller
@RequiredArgsConstructor
public class LoginController {

    @Resource
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid LoginDto loginDto) throws CredentialException {
        userServiceImpl.getUser(loginDto);
        return "redirect:/start-page";
    }
}
