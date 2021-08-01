package com.spring.project.repository;

import com.spring.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrii Barsuk
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
