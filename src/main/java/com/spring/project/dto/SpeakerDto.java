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
public class SpeakerDto {
    private Long id;
    private String firstName;
    private String lastName;

    public String getFullName(){
        return firstName+ " "+lastName;
    }
}
