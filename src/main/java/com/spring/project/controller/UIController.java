package com.spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UIController {

    @GetMapping
    public String redirectToIndex() {
        return "redirect:/index";
    }

    @PostMapping("/back")
    public String backToPreviousPage(HttpServletRequest request) {
        var referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}