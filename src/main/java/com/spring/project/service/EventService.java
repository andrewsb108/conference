package com.spring.project.service;

import com.spring.project.dto.*;
import com.spring.project.model.Event;
import com.spring.project.model.Topic;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createEvent(EventCreateDto eventCreateDto);

    List<EventDto> getAllEvents();

   EventDto getEventById(Long id);

    Optional<Event> updateEvent(EventDto eventDto);

    Long deleteById(Long id);

    Topic addNewTopic(Long eventId, TopicDto topic);

    void registerToEvent(Long id, EventRegisterDto eventRegisterDto);

}
