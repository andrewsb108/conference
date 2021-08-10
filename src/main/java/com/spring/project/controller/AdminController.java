package com.spring.project.controller;

import com.spring.project.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
 * @author Andrii Barsuk
 */
@Log4j2
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Resource
    private UserServiceImpl userServiceImpl;

    @GetMapping
    public String showAdminPage(Model model) {
        model.addAttribute("userActvities");
       return "/admin";
    }
}
