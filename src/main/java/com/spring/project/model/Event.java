package com.spring.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Andrii Barsuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "topicTitle", nullable = false)
    private String title;

    @Column(name = "last_modified")
    private LocalDate scheduledDate;

    private LocalTime scheduledTime;

    @OneToMany
    private List<Topic> topicList;

    @OneToMany
    private List<Participant> participantList;
}
