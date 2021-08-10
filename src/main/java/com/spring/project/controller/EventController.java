package com.spring.project.controller;

import com.spring.project.service.EventService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Log4j2
@Controller
@RequestMapping("/event")
public class EventController {

    @Resource
    private EventService eventService;

    @GetMapping("/all")
    public ModelAndView showAllEvents() {
        ModelAndView modelAndView = new ModelAndView("events-list");
        modelAndView.addObject("events", eventService.getAllEvents());
        return modelAndView;
    }
}
