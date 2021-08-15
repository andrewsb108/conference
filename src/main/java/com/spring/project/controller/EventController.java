package com.spring.project.controller;

import com.spring.project.dto.*;
import com.spring.project.exceptions.EventAlreadyExistException;
import com.spring.project.service.EventService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public String showAllEvent(@ModelAttribute("event") EventDto eventDto, Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "event_list";
    }

//    @GetMapping("/edit/{id}")
//    public String showEditEvent(@PathVariable("id") long id, Model model) {
//        EventDto dto = eventService.getEventById(id);
//        System.out.println(dto);
//        model.addAttribute("event", dto);
//        model.addAttribute("topic", new TopicDto(id, ""));  //todo:??
//        return "event_edit";
//    }

    @GetMapping("/edit/{id}")
    public String showEditEvent(@PathVariable("id") long id, Model model) {
        EventDto dto = eventService.getEventById(id);
        model.addAttribute("event", dto);
        model.addAttribute("topic", new TopicDto());
        model.addAttribute("topics", dto.getTopicList());
        model.addAttribute("participantList", dto.getParticipantList());

        return "event_edit";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable("id") long id, EventDto eventDto) {
        eventService.updateEvent(eventDto);
        return "redirect:/event/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") long id) {
        eventService.deleteById(id);
        return "redirect:/event/all";
    }

    @PostMapping("/{id}/topic/add")
    public String createNewTopic(@PathVariable("id") long id, @ModelAttribute("topic") TopicDto dto) {
        eventService.addNewTopic(id, dto);
        return "redirect:/event/edit/" + id;
    }

    @GetMapping("/{id}/event-reg")
    public String showRegisterToEvent(@PathVariable("id") long id, Model model) {
        model.addAttribute("participant", new EventRegisterDto());
        EventDto eventDto = eventService.getEventById(id);
        System.out.println(eventDto);
        model.addAttribute("event", eventDto);

        return "register_to_event";
    }

    @PostMapping("/{id}/event-reg")
    public String registerToEvent(@PathVariable("id") long id,
                                  @ModelAttribute("participant") EventRegisterDto eventRegisterDto) {
        System.out.println(id);
        eventService.registerToEvent(id, eventRegisterDto);

        return "redirect:/event/all";
    }
}
