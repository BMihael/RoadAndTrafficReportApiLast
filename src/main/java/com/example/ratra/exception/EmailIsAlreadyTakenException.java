package com.example.ratra.exception;

public class EmailIsAlreadyTakenException extends BaseException {
    public EmailIsAlreadyTakenException() {
    }

    public EmailIsAlreadyTakenException(String message) {
        super(message);
    }
}
