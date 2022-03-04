package com.example.demo.exception;

public class EmailIsAlreadyTakenException extends RuntimeException {
    public EmailIsAlreadyTakenException() {
    }

    public EmailIsAlreadyTakenException(String message) {
        super(message);
    }
}
