package com.spring.project.dto;

import com.spring.project.model.Moderator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpeakerDto {
    private long id;
    private String name;
    private Moderator moderator;
}
