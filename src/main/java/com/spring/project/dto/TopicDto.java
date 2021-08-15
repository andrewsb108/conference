package com.spring.project.dto;


import com.spring.project.model.Speaker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicDto {
    private long id;
    private String topicTitle;
    private Speaker speaker;
}
