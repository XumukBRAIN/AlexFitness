package com.example.crossFit.exceptions;

public class AuthenticationInvalidRequestException extends RuntimeException {

    public AuthenticationInvalidRequestException(String message) {
        super(message);
    }
}
