package com.spring.project.repository;

import com.spring.project.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrii Barsuk
 */
@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
