package com.spring.project.repository;

import com.spring.project.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrii Barsuk
 */
@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
}
