package com.example.ratra.exception;

import com.example.ratra.ResponseHandler;
import com.example.ratra.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleLocationNotFoundException(
            LocationNotFoundException exception) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseHandler.generateErrorResponse(HttpStatus.NOT_FOUND, error);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUsernameNotFoundException(
            UsernameNotFoundException exception) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseHandler.generateErrorResponse(HttpStatus.NOT_FOUND, error);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUsernameIsAlreadyTakenException(UsernameIsAlreadyTakenException exception) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseHandler.generateErrorResponse(HttpStatus.BAD_REQUEST, error);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleEmailIsAlreadyTakenException(EmailIsAlreadyTakenException exception) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseHandler.generateErrorResponse(HttpStatus.BAD_REQUEST, error);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException exception) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseHandler.generateErrorResponse(HttpStatus.NOT_FOUND, error);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleSubmitNotFoundException(SubmitNotFoundException exception) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseHandler.generateErrorResponse(HttpStatus.NOT_FOUND, error);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleImageNotFoundException(ImageNotFoundException exception) {
        ErrorResponse error = new ErrorResponse();

        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseHandler.generateErrorResponse(HttpStatus.NOT_FOUND, error);
    }
}
