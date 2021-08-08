package com.spring.project.controller;

import com.spring.project.service.UserService;
import com.spring.project.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Controller
@RequiredArgsConstructor
public class UserController {
    @Resource
    private final UserServiceImpl userServiceImpl;

    @GetMapping(value = {"/logout"})
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:login";
    }

    @GetMapping(value = {"/"})
    public ModelAndView startPage() {
        return new ModelAndView("start-page");
    }

}
