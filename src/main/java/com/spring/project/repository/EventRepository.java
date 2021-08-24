package com.spring.project.repository;

import com.spring.project.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
