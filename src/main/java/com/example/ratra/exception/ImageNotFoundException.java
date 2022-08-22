package com.example.ratra.exception;

public class ImageNotFoundException extends BaseException {
    public ImageNotFoundException() {
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}
