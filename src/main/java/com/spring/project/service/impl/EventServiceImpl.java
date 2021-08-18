package com.spring.project.service.impl;

import com.spring.project.dto.EventCreateDto;
import com.spring.project.dto.EventDto;
import com.spring.project.dto.EventRegisterDto;
import com.spring.project.dto.TopicDto;
import com.spring.project.exceptions.EventNotCreateException;
import com.spring.project.exceptions.EventNotFoundException;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.Event;
import com.spring.project.model.Participant;
import com.spring.project.model.Topic;
import com.spring.project.repository.EventRepository;
import com.spring.project.repository.ParticipantRepository;
import com.spring.project.repository.TopicRepositiry;
import com.spring.project.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    private final TopicRepositiry topicRepositiry;
    private final BusinessMapper businessMapper;
    private final MessageSource messageSource;
    private final ParticipantRepository participantRepository;
//    private final SpeakerRepository speakerRepository;

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
        return businessMapper.convertEventListToEventDto(eventRepository.findAll());
    }

    @Override
    public EventDto getEventById(Long id) {
        return businessMapper.convertEventToEventDto(eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException("No such event was found, id: " + id)));
    }

    @Override
    public Optional<Event> updateEvent(EventDto eventDto) {
        System.out.println(eventDto);
        Event event = businessMapper.convertEventDtoToEventForUpdate(eventDto);
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
    @Transactional
    public Topic addNewTopic(long eventId, TopicDto topicDto) {
        Event current = eventRepository.findById(eventId);
        topicDto.setId(0);
        Topic topic = businessMapper.convertToTopic(topicDto);
        topicRepositiry.save(topic);
        current.getTopicList().add(topic);
        eventRepository.save(current);
        return topic;
    }

    @Override
    @Transactional
    public void registerToEvent(long eventId, EventRegisterDto eventRegisterDto) {
        Event current = eventRepository.findById(eventId);
        Participant participant = businessMapper.convertEventRegisterDtoToParticipant(eventRegisterDto, eventId);
        participantRepository.save(participant);
        current.getParticipantList().add(participant);
        eventRepository.save(current);
    }

//    @Override
//    public List<SpeakerDto> getAllSpeakers() {
//        return businessMapper.convertSpeakerListToSpeakerDtoList(speakerRepository.findAll());
//    }
}
