package com.spring.project.dto;

import com.spring.project.model.Moderator;
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
    private long id;
    private Moderator moderator;
}
