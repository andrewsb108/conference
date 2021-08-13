package com.spring.project.service;

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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
//            Event event = new Event(0, eventCreateDto.getTitle(), LocalDate.parse(eventCreateDto.getScheduledDate(),
//                    DateTimeFormatter.ofPattern("dd.MM.yyyy")), LocalTime.parse(eventCreateDto.getScheduledTime(),
//                    DateTimeFormatter.ofPattern("HH:mm")), new TreeMap<>(), new ArrayList<>());
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
    public Event getEventById(Long id) {
        return  eventRepository.findById(id).orElseThrow(() ->
                  new EventNotFoundException("No such event was found, id: " + id));
    }
}
