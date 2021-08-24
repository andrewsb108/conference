package com.spring.project.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super();
    }

    public EventNotFoundException(String message) {
        super(message);
    }
}
