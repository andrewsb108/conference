package com.spring.project.mapping;

import com.spring.project.dto.*;
import com.spring.project.model.Event;
import com.spring.project.model.Participant;
import com.spring.project.model.Role;
import com.spring.project.model.User;
import com.spring.project.model.enums.UserRole;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.CollationElementIterator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return user;
    }

    public User convertRegistrationDtoToUser(RegistrationDto registrationDto) {
        if (registrationDto == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setCreated(LocalDateTime.now());

        return user;
    }

    public RoleDto convertToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());

        return roleDto;
    }

    public Event convertEventDtoToEvent(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        return Event.builder()
                .title(eventDto.getTitle())
                .scheduled(LocalDateTime.now())
                .topics(eventDto.getTopics())
                .participantList(eventDto.getParticipantList())
                .build();
    }

    public EventDto convertEventToEventDto(Event event) {
        if (event == null) {
            return null;
        }
        return EventDto.builder()
                .title(event.getTitle())
                .scheduled(event.getScheduled())
                .topics(event.getTopics())
                .participantList(event.getParticipantList())
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
