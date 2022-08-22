package com.example.ratra.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseException extends RuntimeException{

    public BaseException(){}

    public BaseException(String message){
        super(message);
        log.info(message);
    }
}
