package com.spring.project.repository;

import com.spring.project.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByIdIn(List<Long> list);
}
