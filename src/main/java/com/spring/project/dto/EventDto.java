package com.spring.project.dto;

import com.spring.project.model.Participant;
import com.spring.project.model.Speaker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Andrii Barsuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private long id;

    @NotEmpty(message = "{valid.reg.not.empty}")
    private String title;

    private LocalDateTime scheduled;

    private Map<String, Speaker> topics;

    private List<Participant> participantList;
}
