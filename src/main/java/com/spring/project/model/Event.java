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
    @CollectionTable(name = "topics")
    @Column(name = "topics")
    private Map<String, Speaker> topics;

    @OneToMany
    private List<Participant> participantList;
}
//todo 1 - Create class Topic (id , title, Speaker)
//todo 2 - Change class Event map->list(topic)
//todo 3 - Change bussiness mapper
//todo 4 - change edit event page for topic section
