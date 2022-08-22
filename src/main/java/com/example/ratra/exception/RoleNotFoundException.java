package com.example.ratra.exception;

public class RoleNotFoundException extends BaseException {

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
