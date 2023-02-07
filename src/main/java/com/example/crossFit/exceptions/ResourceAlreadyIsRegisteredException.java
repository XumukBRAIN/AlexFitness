package com.example.crossFit.exceptions;

public class ResourceAlreadyIsRegisteredException extends RuntimeException {

    public ResourceAlreadyIsRegisteredException() {
    }

    public ResourceAlreadyIsRegisteredException(String message) {
        super(message);
    }
}
