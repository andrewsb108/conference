package com.spring.project.exceptions;

public class TopicNotCreatedException extends RuntimeException {
    public TopicNotCreatedException() {
        super();
    }

    public TopicNotCreatedException(String message) {
        super(message);
    }
}
