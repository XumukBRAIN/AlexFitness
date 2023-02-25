package com.example.crossFit.exceptions;

import com.example.crossFit.security.JwtAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> handleException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError(e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceAlreadyIsRegisteredException.class)
    public ResponseEntity<AppError> handleException(ResourceAlreadyIsRegisteredException e) {
        return new ResponseEntity<>(new AppError(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AuthenticationInvalidException.class)
    public ResponseEntity<AppError> handleException(AuthenticationInvalidException e) {
        return new ResponseEntity<>(new AppError(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<AppError> handlerException(JwtAuthenticationException e) {
        return new ResponseEntity<>(new AppError(e.getMessage(), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientAlreadyIsBlockedException.class)
    public ResponseEntity<AppError> handleException(ClientAlreadyIsBlockedException e) {
        return new ResponseEntity<>(new AppError(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }


}
