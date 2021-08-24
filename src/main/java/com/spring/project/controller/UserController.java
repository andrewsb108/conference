package com.spring.project.controller;

import com.spring.project.dto.EventDto;
import com.spring.project.dto.EventRegisterDto;
import com.spring.project.exceptions.EventAlreadyExistException;
import com.spring.project.exceptions.EventNotFoundException;
import com.spring.project.service.EventService;
import com.spring.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final EventService eventService;
    private final MessageSource messageSource;


    @GetMapping("/index")
    public String showAllEvent(@ModelAttribute("event") EventDto eventDto, Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "index";
    }

    @GetMapping("/{id}/event-reg")
    public String showRegisterToEvent(@PathVariable("id") long eventId,
                                      @ModelAttribute("participant") EventRegisterDto eventRegisterDto,
                                      Model model) {
        try {
            EventDto eventDto = eventService.getEventById(eventId);
            model.addAttribute("event", eventDto);
            model.addAttribute("flag", false);
        } catch (EventNotFoundException ex) {
            model.addAttribute("error_message", messageSource.getMessage("event.not.found",
                    null, LocaleContextHolder.getLocale()));
        }
        return "register_to_event";
    }

    @PostMapping("/{eventId}/event-reg")
    public String registerToEvent(@PathVariable long eventId,
                                  @ModelAttribute("participant") EventRegisterDto eventRegisterDto, Model model) {
        try {
            log.info("speakerValue is {}", eventRegisterDto.getIsSpeaker());
            eventService.registerToEvent(eventId, eventRegisterDto);
        } catch (EventAlreadyExistException e) {
            model.addAttribute("error_message", messageSource.getMessage("event.exist",
                    null, LocaleContextHolder.getLocale()));
        }
        return "redirect:/event/all";
    }
}
