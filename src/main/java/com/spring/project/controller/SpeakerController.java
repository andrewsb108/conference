package com.spring.project.controller;

import com.spring.project.dto.EventDto;
import com.spring.project.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Log4j2
@Controller
@RequiredArgsConstructor
public class SpeakerController {
//    @Autowired
    private final EventService eventService;

    @GetMapping("/board")
    public String showAllEvent(@ModelAttribute("event") EventDto eventDto, Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "dashboard";
    }
}
