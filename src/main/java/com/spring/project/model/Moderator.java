package com.spring.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrii Barsuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "moderators")
public class Moderator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "mobileNumber", nullable = false)
    private String mobileNumber;
    @OneToMany
    private List<Speaker> speakerList;
    @OneToMany
    private List<User> userList;
    @CreatedDate
    private LocalDateTime created = LocalDateTime.now();
    @Column(name = "last_modified")
    @LastModifiedDate
    private LocalDateTime lastModified;
}
