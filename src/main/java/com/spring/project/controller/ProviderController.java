package com.spring.project.controller;

import com.spring.project.ViewRegistration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    ViewRegistration controllerView = new ViewRegistration();


    @GetMapping("/")
    public String getChangeLanguagePage(Model model) {
        model.addAttribute("locale", controllerView);
        return "homepage";
    }

    @GetMapping("/en")
    public String getPageWithEnLang(Model model) {
        model.addAttribute("locale", controllerView);
        return "homepage";
    }

    @GetMapping("/en")
    public String getPageWithUaLang(Model model) {
        model.addAttribute("locale", controllerView);
        return "homepage";
    }

    @GetMapping("/en")
    public String getUserEntryPage(Model model) {
        model.addAttribute("locale", controllerView);
        return "user-entry-page";
    }
}
