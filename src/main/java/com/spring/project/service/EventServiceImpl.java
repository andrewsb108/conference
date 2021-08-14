package com.spring.project.service;

import com.spring.project.dto.CreateTopicDto;
import com.spring.project.dto.EventCreateDto;
import com.spring.project.dto.EventDto;
import com.spring.project.exceptions.EventNotCreateException;
import com.spring.project.exceptions.EventNotFoundException;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.Event;
import com.spring.project.repository.EventRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@Service
public class EventServiceImpl implements EventService {

    @Resource
    private EventRepository eventRepository;

    @Resource
    private BusinessMapper businessMapper;

    @Resource
    private MessageSource messageSource;

    @Override
    public Event createEvent(EventCreateDto eventCreateDto) {
        try {
            Event event = businessMapper.convertEventCreateDtoToEvent(eventCreateDto);
            log.info("Handling save users: " + eventCreateDto);
            return eventRepository.save(event);
        } catch (DataIntegrityViolationException e) {
            throw new EventNotCreateException(messageSource.getMessage("event.not.create", null,
                    LocaleContextHolder.getLocale()) + eventCreateDto.getTitle());
        }
    }

    @Override
    public List<EventDto> getAllEvents() {
        return businessMapper.convertEventDtoToEventGetAll(eventRepository.findAll());
    }

    @Override
    public EventDto getEventById(Long id) {
        return businessMapper.convertEventToEventDto(eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException("No such event was found, id: " + id)));
    }

    @Override
    public Optional<Event> updateEvent(EventDto eventDto) {
        Event event = businessMapper.convertEventDtoToEvent(eventDto);
        return Optional.ofNullable(eventRepository.save(event));
    }

    @Override
    public Long deleteById(long id) {
        try {
            eventRepository.deleteById(id);
        } catch (EventNotFoundException e) {
            log.info("Deleted event with id: " + id);
        }
        return id;
    }

    @Override
    public void addTopic(CreateTopicDto topic) {
        //  System.out.println(topic);
        Optional<Event> eventOptional = eventRepository.findById(topic.getId());
        if (eventOptional.isPresent()) {
            Event current = eventOptional.get();
            //    System.out.println(current);
            current.getTopics().put(topic.getTopic(), null);
            eventRepository.save(current);
            //  System.out.println(current);
        }
    }
}
