package com.spring.project.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Andrii Barsuk
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "participants", uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "user_id"}))
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_speaker")
    private Boolean isSpeaker;

    private String nickName;
}
