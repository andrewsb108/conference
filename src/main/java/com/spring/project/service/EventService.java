package com.spring.project.service;

import com.spring.project.dto.EventDto;
import com.spring.project.model.Event;

import java.util.List;

public interface EventService {
    Event createEvent(EventDto eventDto);

    List<EventDto> getAllEvents();

    Event getEventById(Long id);

}
