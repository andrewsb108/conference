package com.spring.project.mapping;

import com.spring.project.dto.*;
import com.spring.project.model.Event;
import com.spring.project.model.Participant;
import com.spring.project.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return user;
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
                .topics(new TreeMap<>())
                .participantList(new ArrayList<>())
                .build();
    }

    public EventDto convertEventToEventDto(Event event) {
        if (event == null) {
            return null;
        }
        System.out.println("Event from DB:" +event);
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .scheduledDate(event.getScheduledDate())
                .scheduledTime(event.getScheduledTime())
                .topics(event.getTopics())
                .participantList(event.getParticipantList())
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
                .topics(new TreeMap<>())
                .participantList(new ArrayList<>())
                .build();
    }

    public List<EventDto> convertEventDtoToEventGetAll(List<Event> events) {
        if (events == null) {
            return null;
        }
        List<EventDto> list = new ArrayList<>();
        for (Event event : events) {
            list.add(convertEventToEventDto(event));
        }
        return list;
    }

    public Participant convertParticipantDtoToParticipant(ParticipantDto participantDto) {
        if (participantDto == null) {
            return null;
        }
        return Participant.builder()
                .name(participantDto.getName())
                .email(participantDto.getEmail())
                .isPresent(true)
                .registered(participantDto.getRegistered())
                .build();
    }

    public ParticipantDto convertParticipantToParticipantDto(Participant participant) {
        if (participant == null) {
            return null;
        }
        return ParticipantDto.builder()
                .name(participant.getName())
                .email(participant.getEmail())
                .isPresent(true)
                .registered(participant.getRegistered())
                .build();
    }
}
