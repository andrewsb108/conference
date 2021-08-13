package com.spring.project.exceptions;

public class EventNotCreateException extends RuntimeException {
    public EventNotCreateException() {
        super();
    }

    public EventNotCreateException(String message) {
        super(message);
    }

    public EventNotCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventNotCreateException(Throwable cause) {
        super(cause);
    }
}
