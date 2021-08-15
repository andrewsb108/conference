package com.spring.project.repository;

import com.spring.project.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepositiry extends JpaRepository<Topic, Long> {
}
