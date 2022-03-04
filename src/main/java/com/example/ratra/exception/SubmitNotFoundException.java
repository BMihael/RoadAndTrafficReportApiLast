package com.example.ratra.exception;

public class SubmitNotFoundException extends RuntimeException {
    public SubmitNotFoundException() {
    }

    public SubmitNotFoundException(String message) {
        super(message);
    }
}
