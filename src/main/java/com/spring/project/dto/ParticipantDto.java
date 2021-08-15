package com.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author Andrii Barsuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantDto {
    private long id;
    private long eventId;
    private String firstName;
    private String lastName;
    private boolean isSpeaker;


//    private long id;
//
//    @NotEmpty(message = "{valid.reg.not.empty}")
//    private String name;
//
//    @Email(message = "{valid.reg.email}")
//    @NotEmpty(message = "{valid.reg.not_empty}")
//    @Size(min = 2, max = 25, message = "{valid.reg.email.size}")
//    private String email;
//
//    private boolean isPresent;
//
//    private LocalDateTime registered;
}
