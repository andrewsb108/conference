package com.spring.project.controller;

import com.spring.project.dto.*;
import com.spring.project.exceptions.EventAlreadyExistException;
import com.spring.project.exceptions.EventNotFoundException;
import com.spring.project.exceptions.TopicNotCreatedException;
import com.spring.project.model.Topic;
import com.spring.project.model.User;
import com.spring.project.repository.TopicRepository;
import com.spring.project.service.EventService;
import com.spring.project.service.TopicService;
import com.spring.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequestMapping("/event")
@RequiredArgsConstructor
@Controller
public class EventController {

    private final EventService eventService;
    private final MessageSource messageSource;
    private final TopicRepository topicRepository;
    private final TopicService topicService;
    private final UserService userService;

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

    @GetMapping("/edit/{eventId}")
    public String showEditEvent(@PathVariable long eventId, Model model) {
        try {
            EventDto dto = eventService.getEventById(eventId);
            model.addAttribute("event", dto);
            model.addAttribute("topic", new TopicDto());
            List<Topic> topics = topicRepository.findByEventId(eventId);
            model.addAttribute("topics", topics);
            List<UserDto> users = userService.getAllUsers();
            model.addAttribute("users", users);
        } catch (EventNotFoundException ex) {
            model.addAttribute("error_message", messageSource.getMessage("event.not.found",
                    null, LocaleContextHolder.getLocale()));
        }
        return "event_edit";
    }

    @PostMapping("/edit/{eventId}")
    public String assignSpeaker(@PathVariable long eventId, UserDto userDto) {
        return "redirect:/event/edit/" + eventId;
    }

    @PostMapping("/update/{eventId}")
    public String updateEvent(@PathVariable long eventId, @ModelAttribute("event") EventDto eventDto) {
        eventService.updateEvent(eventDto);
        return "redirect:/event/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable long id) {
        eventService.deleteById(id);
        return "redirect:/event/all";
    }

    @PostMapping("/{eventId}/topic/add")
    public String createNewTopic(@PathVariable long eventId, @ModelAttribute("topic") TopicDto dto, Model model) {
        try {
            eventService.addNewTopic(eventId, dto);
        } catch (TopicNotCreatedException e) {
            model.addAttribute("error_message", messageSource.getMessage("topic.not.created",
                    null, LocaleContextHolder.getLocale()));
        }
        return "redirect:/event/edit/" + eventId;
    }

    @PostMapping("/topic/edit/{topicId}")
    public String editTopic(@PathVariable long topicId, @ModelAttribute("topic") TopicDto topicDto) {
        topicService.editTopic(topicDto);

        return "redirect:event/all";
    }
}




