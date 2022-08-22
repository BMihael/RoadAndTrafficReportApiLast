package com.example.ratra.exception;

public class LocationNotFoundException extends BaseException{
    public LocationNotFoundException() {
    }

    public LocationNotFoundException(String message) {
        super(message);
    }
}
