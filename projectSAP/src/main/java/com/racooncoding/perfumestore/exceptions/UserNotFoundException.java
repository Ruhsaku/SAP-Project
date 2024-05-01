package com.racooncoding.perfumestore.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User with such an email does not exist.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
