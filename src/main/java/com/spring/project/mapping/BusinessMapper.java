package com.spring.project.mapping;

import com.spring.project.dto.*;
import com.spring.project.model.Event;
import com.spring.project.model.Participant;
import com.spring.project.model.Topic;
import com.spring.project.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BusinessMapper {

    public UserDto convertUserToUserDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail()).build();
    }

    public User convertFromUpdateUserDtoToUser(UpdateUserDto updateUserDto) {
        if (updateUserDto == null) {
            return null;
        }
        return User.builder()
                .firstName(updateUserDto.getFirstName())
                .lastName(updateUserDto.getLastName())
                .build();
    }

    public List<UserDto> convertUserToUserDtoGetAll(List<User> users) {
        if (users == null) {
            return null;
        }
        List<UserDto> list = new ArrayList<>();
        for (User user : users) {
            list.add(convertUserToUserDto(user));
        }
        return list;
    }

    public User convertUserDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }

    public User convertRegistrationDtoToUser(RegistrationDto registrationDto) {
        if (registrationDto == null) {
            return null;
        }
        User user = User.builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .created(LocalDateTime.now())
                .build();

        user.setEnabled(true);

        return user;
    }

    public Event convertEventDtoToEvent(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        return Event.builder()
                .id(eventDto.getId())
                .title(eventDto.getTitle())
                .scheduledDate(eventDto.getScheduledDate())
                .scheduledTime(eventDto.getScheduledTime())
                .topicList(eventDto.getTopicList().stream().map(topic -> convertTopicDtoToTopic(topic)).collect(Collectors.toList()))
                .participantList(eventDto.getParticipantList().stream().map(p->convertToParticipant(p)).collect(Collectors.toList()))
                .build();
    }

    public EventDto convertEventToEventDto(Event event) {
        if (event == null) {
            return null;
        }
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .scheduledDate(event.getScheduledDate())
                .scheduledTime(event.getScheduledTime())
                .topicList(event.getTopicList().stream().map(topic -> convertToTopic(topic)).collect(Collectors.toList()))
                .participantList(event.getParticipantList().stream().map(p->convertToParticipantDto(p)).collect(Collectors.toList()))
                .build();
    }

    public Event convertEventCreateDtoToEvent(EventCreateDto eventCreateDto) {
        if (eventCreateDto == null) {
            return null;
        }
        return Event.builder()
                .title(eventCreateDto.getTitle())
                .scheduledDate(LocalDate.parse(eventCreateDto.getScheduledDate(),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .scheduledTime(LocalTime.parse(eventCreateDto.getScheduledTime(),
                        DateTimeFormatter.ofPattern("HH:mm")))
                .topicList(new ArrayList<>())
                .participantList(new ArrayList<>())
                .build();
    }

    public List<EventDto> convertEventListToEventDto(List<Event> events) {
        if (events == null) {
            return null;
        }
        List<EventDto> list = new ArrayList<>();
        for (Event event : events) {
            list.add(convertEventToEventDto(event));
        }
        return list;
    }

    public Participant convertToParticipant(ParticipantDto participantDto) {
        if (participantDto == null) {
            return null;
        }
        return Participant.builder()
                .id(participantDto.getId())
                .eventId(participantDto.getEventId())
                .firstName(participantDto.getFirstName())
                .lastName(participantDto.getLastName())
                .isSpeaker(participantDto.isSpeaker())
                .build();
    }

    public ParticipantDto convertToParticipantDto(Participant participant) {
        if (participant == null) {
            return null;
        }
        return ParticipantDto.builder()
                .id(participant.getId())
                .eventId(participant.getEventId())
                .firstName(participant.getFirstName())
                .lastName(participant.getLastName())
                .isSpeaker(participant.isSpeaker())
                .build();
    }

    public TopicDto convertToTopic(Topic topic) {
        if (topic == null) {
            return null;
        }
        return TopicDto.builder()
                .id(topic.getId())
                .topicTitle(topic.getTitle())
                .speaker(topic.getSpeaker())
                .build();
    }

    public Topic convertTopicDtoToTopic(TopicDto topicDto) {
        if (topicDto == null) {
            return null;
        }
        return Topic.builder()
                .id(topicDto.getId())
                .title(topicDto.getTopicTitle())
                .speaker(topicDto.getSpeaker())
                .build();
    }

    public Participant convertEventRegisterDtoToParticipant(EventRegisterDto eventRegisterDto, long eventId) {
        if (eventRegisterDto == null) {
            return null;
        }
        return Participant.builder()
                .eventId(eventId)
                .firstName(eventRegisterDto.getFirstName())
                .lastName(eventRegisterDto.getLastName())
                .isSpeaker(eventRegisterDto.isSpeaker())
                .build();
    }


}
