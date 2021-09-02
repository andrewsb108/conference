package com.spring.project.service.impl;

import com.spring.project.dto.EventCreateDto;
import com.spring.project.dto.EventDto;
import com.spring.project.dto.EventRegisterDto;
import com.spring.project.dto.TopicDto;
import com.spring.project.exceptions.EventAlreadyExistException;
import com.spring.project.exceptions.EventNotFoundException;
import com.spring.project.exceptions.ParticipantAlreadyRegistered;
import com.spring.project.exceptions.TopicNotCreatedException;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.Event;
import com.spring.project.model.Topic;
import com.spring.project.model.User;
import com.spring.project.model.enums.Role;
import com.spring.project.repository.EventRepository;
import com.spring.project.repository.ParticipantRepository;
import com.spring.project.repository.TopicRepository;
import com.spring.project.repository.UserRepository;
import com.spring.project.security.SecurityService;
import com.spring.project.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Andrii Barsuk
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    private final TopicRepository topicRepository;
    private final BusinessMapper businessMapper;
    private final MessageSource messageSource;
    private final ParticipantRepository participantRepository;
    private final SecurityService securityService;
    private final UserRepository userRepository;

    @Override
    public Event createEvent(EventCreateDto eventCreateDto) {
        try {
            var event = businessMapper.convertEventCreateDtoToEvent(eventCreateDto);
            log.info("Handling save users: " + eventCreateDto);
            return eventRepository.save(event);
        } catch (DataIntegrityViolationException e) {
            throw new EventAlreadyExistException(messageSource.getMessage("event.exists", null,
                    LocaleContextHolder.getLocale()) + eventCreateDto.getTitle());
        }
    }

    @Override
    public List<EventDto> getAllEvents() {
        return businessMapper.convertEventListToEventDto(eventRepository.findAll());
    }

    @Override
    public EventDto getEventById(Long id) {
        var event = eventRepository.findById(id);
        return event.map(businessMapper::convertEventToEventDto)
                .orElseThrow(() -> new EventNotFoundException("event.not.found"));
    }

    @Override
    public Optional<Event> updateEvent(EventDto eventDto) {
        var event = businessMapper.convertEventDtoToEventForUpdate(eventDto);
        return Optional.of(eventRepository.save(event));
    }

    @Override
    public Long deleteById(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (EventNotFoundException e) {
            log.info("Deleted event with id: " + id);
        }
        return id;
    }

    @Override
    @Transactional
    public Topic addNewTopic(Long eventId, TopicDto topicDto) {
        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new TopicNotCreatedException("topic.not.created"));
        var topic = businessMapper.convertToTopic(topicDto);
        topic.setEvent(event);
        topicRepository.save(topic);
        return topic;
    }

    @Override
    @Transactional
    public void registerToEvent(Long eventId, EventRegisterDto eventRegisterDto) {
        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventAlreadyExistException(
                        messageSource.getMessage("register.at.the.same.event", null,
                                LocaleContextHolder.getLocale()) + eventRegisterDto.getNickName()));
        var participant = businessMapper
                .convertEventRegisterDtoToParticipant(eventRegisterDto, event);
        var user = securityService.getCurrentLoggedUser();
        if (eventRegisterDto.getIsSpeaker()) {
            Set<Role> roles = user.getRoles();
            roles.add(Role.SPEAKER);
            user.setRoles(roles);
            userRepository.save(user);
        }
        checkIfAlreadyRegistered(user, event);

        participant.setUser(user);
        try {
            participantRepository.save(participant);
        } catch (Exception ex) {
            throw new ParticipantAlreadyRegistered();
        }
    }

    @Override
    public Page<Event> findAllPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return eventRepository.findAll(pageable);
    }

    private void checkIfAlreadyRegistered(User user, Event event) {
        var participant = participantRepository.findByUserIdAndEventId(user.getId(), event.getId());
        if (participant.isPresent()) {
            throw new ParticipantAlreadyRegistered("You are already registered for this event");
        }
    }
}