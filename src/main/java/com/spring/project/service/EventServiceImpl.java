package com.spring.project.service;

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
import java.util.List;

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
    public Event createEvent(EventDto eventDto) {
        try {
            Event event = businessMapper.convertEventDtoToEvent(eventDto);
            return eventRepository.save(event);
        } catch (DataIntegrityViolationException e) {
            throw new EventNotCreateException(messageSource.getMessage("event.not.create", null,
                    LocaleContextHolder.getLocale()) + eventDto.getTitle());
        }
    }

    @Override
    public List<EventDto> getAllEvents() {
        return businessMapper.convertEventDtoToEventGetAll(eventRepository.findAll());
    }

    @Override
    public Event getEventById(Long id) {
          eventRepository.findById(id).orElseThrow(() ->
                  new EventNotFoundException("No such event was found, id: " + id));
        return null;
    }
}
