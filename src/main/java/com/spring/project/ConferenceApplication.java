package com.spring.project;

import com.spring.project.model.Event;
import com.spring.project.model.User;
import com.spring.project.model.enums.Role;
import com.spring.project.repository.EventRepository;
import com.spring.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

@SpringBootApplication
public class ConferenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceApplication.class, args);
    }
}

@Component
class MyRunner implements CommandLineRunner {


    @Autowired
    private UserRepository repository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    BCryptPasswordEncoder encoder;


    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("adminovich");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(encoder.encode("123"));
        admin.setRoles(Set.of(Role.MODERATOR));
        repository.save(admin);

        User speaker = new User();
        speaker.setFirstName("speaker");
        speaker.setLastName("speakerovich");
        speaker.setEmail("speaker@gmail.com");
        speaker.setPassword(encoder.encode("456"));
        speaker.setRoles(Set.of(Role.SPEAKER));
        repository.save(speaker);

        Random rnd = new Random();
        Event event = new Event(0, "event 1", LocalDate.now().plusDays(rnd.nextInt(100)), LocalTime.now().plusMinutes(rnd.nextInt(100)), new ArrayList<>(), new ArrayList<>());
        Event event1 = new Event(0, "event 2", LocalDate.now().plusDays(rnd.nextInt(100)), LocalTime.now().plusMinutes(rnd.nextInt(100)), new ArrayList<>(), new ArrayList<>());
        Event event2 = new Event(0, "event 3", LocalDate.now().plusDays(rnd.nextInt(100)), LocalTime.now().plusMinutes(rnd.nextInt(100)), new ArrayList<>(), new ArrayList<>());

        eventRepository.save(event);
        eventRepository.save(event1);
        eventRepository.save(event2);
    }
}