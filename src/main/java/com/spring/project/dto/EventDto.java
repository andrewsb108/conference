package com.spring.project.dto;

import com.spring.project.model.Participant;
import com.spring.project.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Andrii Barsuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Long id;

    @NotEmpty(message = "{valid.reg.not.empty}")
    private String title;

    private String scheduledDate;

    private List<Topic> topics;

    private List<Participant> participants;
}
