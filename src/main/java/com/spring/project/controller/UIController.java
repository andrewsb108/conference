package com.spring.project.controller;

import com.spring.project.dto.RegistrationDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Log4j2
@Controller
public class UIController {
    @GetMapping(value = {"/"})
    public ModelAndView startPage() {
        return new ModelAndView("start-page");
    }


//    @GetMapping(value = {"/login"})
//    public String login() {
//        return "login";
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
