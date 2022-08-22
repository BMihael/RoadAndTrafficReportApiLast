package com.example.ratra.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {

    public UsernameNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameNotFoundException(String msg) {
        super(msg);
    }
}
