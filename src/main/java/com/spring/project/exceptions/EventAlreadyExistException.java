package com.spring.project.exceptions;

public class EventAlreadyExistException extends RuntimeException {
    public EventAlreadyExistException() {
        super();
    }

    public EventAlreadyExistException(String message) {
        super(message);
    }

    public EventAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
