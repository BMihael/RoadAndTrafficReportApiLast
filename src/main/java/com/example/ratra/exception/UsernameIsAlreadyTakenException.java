package com.example.ratra.exception;

public class UsernameIsAlreadyTakenException extends RuntimeException {

    public UsernameIsAlreadyTakenException() {
    }

    public UsernameIsAlreadyTakenException(String message) {
        super(message);
    }
}
