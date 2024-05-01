package com.racooncoding.perfumestore.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException() {
        super("Email is already being used.");
    }

    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
