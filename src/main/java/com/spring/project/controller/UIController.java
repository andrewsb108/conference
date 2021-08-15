package com.spring.project.controller;

import com.spring.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UIController {

    private final UserRepository userRepository;
}
