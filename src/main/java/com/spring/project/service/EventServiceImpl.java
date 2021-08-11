package com.spring.project.service;

import com.spring.project.dto.EventDto;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.Event;
import com.spring.project.repository.EventRepository;
import lombok.extern.log4j.Log4j2;
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

    public List<EventDto> getAllEvents() {
        return businessMapper.convertEventDtoToEventGetAll(eventRepository.findAll());
    }

    @Override
    public Event updateEvents(EventDto eventDto) {
        return null;
    }


}
