package com.spring.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "last_modified")

    private LocalDate scheduledDate;

    private LocalTime scheduledTime;

    @ElementCollection
    @CollectionTable(name = "phone_register")
    @Column(name = "since")
    private Map<String, Speaker> topics;

    @OneToMany
    private List<Participant> participantList;
}
