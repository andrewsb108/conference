package com.spring.project.service;

import com.spring.project.dto.EventCreateDto;
import com.spring.project.dto.EventDto;
import com.spring.project.dto.EventRegisterDto;
import com.spring.project.dto.TopicDto;
import com.spring.project.model.Event;
import com.spring.project.model.Topic;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createEvent(EventCreateDto eventCreateDto);

    List<EventDto> getAllEvents();

    EventDto getEventById(Long id);

    Optional<Event> updateEvent(EventDto eventDto);

    Long deleteById(Long id);

    Topic addNewTopic(Long eventId, TopicDto topic);

    void registerToEvent(Long eventId, EventRegisterDto eventRegisterDto);

    Page<Event> findAllPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
