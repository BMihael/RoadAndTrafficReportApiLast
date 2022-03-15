package com.example.ratra.exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException() {
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}
