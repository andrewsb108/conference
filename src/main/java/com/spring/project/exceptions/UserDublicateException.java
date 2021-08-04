package com.spring.project.exceptions;

public class UserDublicateException extends RuntimeException {
    public UserDublicateException() {
        super();
    }

    public UserDublicateException(String message) {
        super(message);
    }
}
