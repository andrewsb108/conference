package com.spring.project.service;

import com.spring.project.dto.TopicDto;
import com.spring.project.model.Topic;

public interface TopicService {
    Topic editTopic(TopicDto topicDto);
}
