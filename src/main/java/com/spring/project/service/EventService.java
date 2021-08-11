package com.spring.project.service;

import com.spring.project.dto.EventDto;
import com.spring.project.model.Event;

import java.util.List;

public interface EventService {
    public List<EventDto> getAllEvents();
    Event updateEvents(EventDto eventDto);
}
