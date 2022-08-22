package com.example.ratra.exception;

public class UsernameIsAlreadyTakenException extends BaseException {

    public UsernameIsAlreadyTakenException() {}

    public UsernameIsAlreadyTakenException(String message) {
        super(message);
    }
}
