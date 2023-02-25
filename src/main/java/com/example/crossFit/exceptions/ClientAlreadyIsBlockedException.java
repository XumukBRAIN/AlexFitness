package com.example.crossFit.exceptions;

public class ClientAlreadyIsBlockedException extends RuntimeException {

    public ClientAlreadyIsBlockedException(String message) {
        super(message);
    }
}
