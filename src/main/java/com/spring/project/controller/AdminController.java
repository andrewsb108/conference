//package com.spring.project.controller;
//
//import com.spring.project.dto.RegistrationDto;
//import com.spring.project.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//
//
///**
// * @author Andrii Barsuk
// */
//@Log4j2
//@Controller
//@RequestMapping("/admin")
//@RequiredArgsConstructor
//public class AdminController {
//
//    @Resource
//    private UserService userService;
//
//    @GetMapping("/users")
//    public String showAllUsers(@ModelAttribute("user") RegistrationDto registrationDto, Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "";
//    }
//
//
//}
