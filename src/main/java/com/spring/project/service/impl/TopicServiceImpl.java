package com.spring.project.service.impl;

import com.spring.project.dto.TopicDto;
import com.spring.project.model.Topic;
import com.spring.project.repository.TopicRepository;
import com.spring.project.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public Topic editTopic(TopicDto topicDto) {

        Topic topic = Topic.builder()
                .id(topicDto.getId())
                .title(topicDto.getTitle())
                .speaker(topicDto.getSpeaker())
                .build();

        return topicRepository.save(topic);
    }
}
