package com.spring.project.service;

import com.spring.project.dto.EventCreateDto;
import com.spring.project.dto.EventDto;
import com.spring.project.model.Event;

import java.util.List;

public interface EventService {
    Event createEvent(EventCreateDto eventCreateDto);

    List<EventDto> getAllEvents();

    Event getEventById(Long id);

}
