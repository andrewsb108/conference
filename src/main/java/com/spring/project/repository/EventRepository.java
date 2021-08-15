package com.spring.project.repository;

import com.spring.project.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findById(long id);
}
