package com.spring.project.controller;

import com.spring.project.dto.RegistrationDto;
import lombok.extern.log4j.Log4j2;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;


import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class UIController {
    @GetMapping(value = {"/"})
    public ModelAndView startPage() {
        return new ModelAndView("start-page");
    }

//    @GetMapping(value = "/")
//    public String startPage() {
//        return "start-page";
//    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @GetMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("registrationDto", new RegistrationDto());
        return modelAndView;
    }
}
