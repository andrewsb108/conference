package com.spring.project.controller;

import com.spring.project.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Controller
@RequestMapping("/")
public class UserController {

    @Resource
    private UserService userService;


}
