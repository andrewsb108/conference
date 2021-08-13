package com.spring.project.controller;

import com.spring.project.dto.EventCreateDto;
import com.spring.project.dto.EventDto;
import com.spring.project.exceptions.EventAlreadyExistException;
import com.spring.project.service.EventService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Log4j2
@Controller
@RequestMapping("/event")
public class EventController {

    @Resource
    private EventService eventService;

    @Resource
    private MessageSource messageSource;

    @GetMapping
    public String showEventPage(@ModelAttribute("event") EventCreateDto eventCreateDto) {
        return "event_create";
    }

    @GetMapping("/all")
    public String showAllEvent(@ModelAttribute("event") EventDto eventDto, Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "event_list";
    }

    @PostMapping("/create")
    public String createEvent(@ModelAttribute("event") EventCreateDto eventCreateDto,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "event_create";
        }
        try {
            eventService.createEvent(eventCreateDto);
            model.addAttribute("message", messageSource.getMessage("event.create.success",
                    null, LocaleContextHolder.getLocale()));
        } catch (EventAlreadyExistException e) {
            model.addAttribute("error_message", messageSource.getMessage("reg.login.not.unique",
                    null, LocaleContextHolder.getLocale()) + eventCreateDto.getTitle());
        }
        return "redirect:/event/all";
    }
}
