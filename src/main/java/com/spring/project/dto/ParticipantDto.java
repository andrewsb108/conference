package com.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andrii Barsuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantDto {
    private Long id;
    private Long eventId;
    private String firstName;
    private String lastName;
    private boolean isSpeaker;
}

