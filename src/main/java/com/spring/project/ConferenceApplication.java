package com.spring.project;

import com.spring.project.model.Event;
import com.spring.project.model.Topic;
import com.spring.project.model.User;
import com.spring.project.model.enums.Role;
import com.spring.project.repository.EventRepository;
import com.spring.project.repository.TopicRepository;
import com.spring.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
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
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    BCryptPasswordEncoder encoder;


    @Override
    public void run(String... args) throws Exception {
        var moderator = User.builder()
                .firstName("moderator")
                .lastName("mod")
                .email("admin@gmail.com")
                .password(encoder.encode("111"))
                .roles(Set.of(Role.MODERATOR))
                .enabled(true)
                .build();
        var user = User.builder()
                .firstName("user")
                .lastName("user")
                .email("user@gmail.com")
                .password(encoder.encode("111"))
                .roles(Set.of(Role.USER))
                .enabled(true)
                .build();

        var user2 = User.builder()
                .firstName("user2")
                .lastName("user")
                .email("user2@gmail.com")
                .password(encoder.encode("111"))
                .roles(Set.of(Role.SPEAKER))
                .enabled(true)
                .build();
        userRepository.saveAll(List.of(moderator, user, user2));

        var topicOne = Topic.builder()
                .title("topic1")
                .build();
        var topicTwo = Topic.builder()
                .title("topic2")
                .build();
        var rnd = new Random();
        var event = Event.builder()
                .title("event 1")
                .scheduledDate(LocalDateTime.now().plusDays(rnd.nextInt(100)))
                .topics(List.of(topicOne, topicTwo))
                .build();
        var event2 = Event.builder()
                .title("event 2")
                .scheduledDate(LocalDateTime.now().plusDays(rnd.nextInt(100)))
                .build();
        var event3 = Event.builder()
                .title("event 3")
                .scheduledDate(LocalDateTime.now().plusDays(rnd.nextInt(100)))
                .build();

        var event4 = Event.builder()
                .title("event 4")
                .scheduledDate(LocalDateTime.now().plusDays(rnd.nextInt(100)))
                .build();

        var event5 = Event.builder()
                .title("event 5")
                .scheduledDate(LocalDateTime.now().plusDays(rnd.nextInt(100)))
                .build();

        var event6 = Event.builder()
                .title("event 6")
                .scheduledDate(LocalDateTime.now().plusDays(rnd.nextInt(100)))
                .build();

        var event7 = Event.builder()
                .title("event 7")
                .scheduledDate(LocalDateTime.now().plusDays(rnd.nextInt(100)))
                .build();

        var event8 = Event.builder()
                .title("event 8")
                .scheduledDate(LocalDateTime.now().plusDays(rnd.nextInt(100)))
                .build();
        topicOne.setEvent(event);
        topicTwo.setEvent(event);
        topicRepository.saveAll(List.of(topicOne, topicTwo));
        eventRepository.saveAll(List.of(event, event2, event3, event4, event5, event6, event7, event8));
    }
}