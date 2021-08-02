package com.spring.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * @author Andrii Barsuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "speakers")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;



    @ManyToOne
    private Moderator moderator;
}
