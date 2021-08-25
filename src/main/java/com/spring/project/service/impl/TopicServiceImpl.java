package com.spring.project.service.impl;

import com.spring.project.dto.TopicDto;
import com.spring.project.mapping.BusinessMapper;
import com.spring.project.model.Topic;
import com.spring.project.repository.TopicRepository;
import com.spring.project.repository.UserRepository;
import com.spring.project.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final BusinessMapper businessMapper;

    @Override
    public Topic editTopic(TopicDto topicDto) {

        Topic topic = Topic.builder()
                .id(topicDto.getId())
                .title(topicDto.getTitle())
                .speaker(topicDto.getSpeaker())
                .build();

        return topicRepository.save(topic);
    }

    @Override
    public void assignSpeaker(Long topicId, Long speakerId) {
        var speaker = userRepository.findById(speakerId).orElseThrow();
        var topic = topicRepository.findById(topicId).orElseThrow();

        topic.setSpeaker(speaker);
        topicRepository.save(topic);
    }

    @Override
    public TopicDto getTopic(Long id) {
        return businessMapper.convertToTopicDto(topicRepository.findById(id)
                .orElseThrow());
    }

}
