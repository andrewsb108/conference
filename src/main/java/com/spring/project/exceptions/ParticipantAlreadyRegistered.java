package com.spring.project.exceptions;

public class ParticipantAlreadyRegistered extends RuntimeException {
    public ParticipantAlreadyRegistered() {
        super();
    }

    public ParticipantAlreadyRegistered(String message) {
        super(message);
    }
}
