package com.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    private LocalDate scheduledDate;

    private LocalTime scheduledTime;

//    private Map<String, Speaker> topics;

    private List<TopicDto> topicList;

    private List<ParticipantDto> participantList;
}
