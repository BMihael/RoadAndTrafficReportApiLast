package com.example.demo.exception;

public class SubmitNotFoundException extends RuntimeException {
    public SubmitNotFoundException() {
    }

    public SubmitNotFoundException(String message) {
        super(message);
    }
}
