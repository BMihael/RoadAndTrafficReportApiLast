package com.example.ratra.exception;

public class SubmitNotFoundException extends BaseException {
    public SubmitNotFoundException() {
    }

    public SubmitNotFoundException(String message) {
        super(message);
    }
}
