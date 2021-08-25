package com.spring.project.repository;

import com.spring.project.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByUserIdAndEventId(Long userId, Long eventId);

    List<Participant> findAllByUserId(Long userId);
}
