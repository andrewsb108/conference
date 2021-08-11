package com.spring.project.controller;

import com.spring.project.dto.EventDto;
import com.spring.project.service.EventService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Log4j2
@Controller
@RequestMapping("/event")
public class EventController {

    @Resource
    private EventService eventService;

    @GetMapping
    public String showEventPage() {
        return "create-event";
    }

    @GetMapping("/events")
    public String showAllEvent(@ModelAttribute("event") EventDto eventDto, Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events-list";
    }

    //todo: make updateEventsList method
}
