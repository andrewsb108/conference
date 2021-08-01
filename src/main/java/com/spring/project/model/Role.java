package com.spring.project.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Andrii Barsuk
 */
@Entity
public class Role {
    @Id
    private String id;
    @Column(name = "role", nullable = false)
    private String role;
}
