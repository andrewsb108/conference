package com.spring.project.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Andrii Barsuk
 */
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role", nullable = false)
    private String role;
}
