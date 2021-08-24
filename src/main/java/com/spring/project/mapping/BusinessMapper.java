package com.spring.project.mapping;

import com.spring.project.dto.*;
import com.spring.project.model.Event;
import com.spring.project.model.Participant;
import com.spring.project.model.Topic;
import com.spring.project.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BusinessMapper {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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

    public User convertRegistrationDtoToUser(RegistrationDto registrationDto) {
        if (registrationDto == null) {
            return null;
        }
        var user = User.builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .created(LocalDateTime.now())
                .build();

        user.setEnabled(true);

        return user;
    }

//    public Event convertEventDtoToEvent(EventDto eventDto) {
//        if (eventDto == null) {
//            return null;
//        }
//        return Event.builder()
//                .title(eventDto.getTitle())
//                .scheduledDate(LocalDateTime.parse(eventDto.getScheduledDate(), formatter))
//                .participants(eventDto.getParticipants().stream()
//                        .map(this::convertToParticipant).collect(Collectors.toList()))
//                .build();
//    }

    public Event convertEventDtoToEventForUpdate(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }

        return Event.builder()
                .id(eventDto.getId())
                .title(eventDto.getTitle())
                .scheduledDate(LocalDateTime.parse(eventDto.getScheduledDate()))
                .build();
    }

    public EventDto convertEventToEventDto(Event event) {
        if (event == null) {
            return null;
        }
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .scheduledDate(event.getScheduledDate().format(formatter))
                .participants(event.getParticipants().stream()
                        .map(this::convertToParticipantDto).collect(Collectors.toList()))
                .build();
    }

    public Event convertEventCreateDtoToEvent(EventCreateDto eventCreateDto) {
        if (eventCreateDto == null) {
            return null;
        }
        return Event.builder()
                .id(eventCreateDto.getId())
                .title(eventCreateDto.getTitle())
                .scheduledDate(LocalDateTime.parse(eventCreateDto.getScheduledDate(), formatter))
                .participants(new ArrayList<>())
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
                .firstName(participant.getFirstName())
                .lastName(participant.getLastName())
                .isSpeaker(participant.isSpeaker())
                .build();
    }

    public TopicDto convertToTopicDto(Topic topic) {
        if (topic == null) {
            return null;
        }
        return TopicDto.builder()
                .title(topic.getTitle())
                .build();
    }

    public Topic convertToTopic(TopicDto topicDto) {
        if (topicDto == null) {
            return null;
        }
        return Topic.builder()
                .title(topicDto.getTitle())
                .build();
    }

    public Participant convertEventRegisterDtoToParticipant(EventRegisterDto eventRegDto, Event event) {
        if (eventRegDto == null) {
            return null;
        }
        return Participant.builder()
                .firstName(eventRegDto.getFirstName())
                .lastName(eventRegDto.getLastName())
                .isSpeaker(eventRegDto.getIsSpeaker())
                .event(event)
                .build();
    }
}

