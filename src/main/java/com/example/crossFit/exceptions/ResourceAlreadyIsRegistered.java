package com.example.crossFit.exceptions;

public class ResourceAlreadyIsRegistered extends ResourceNotFoundException {

    public ResourceAlreadyIsRegistered(String message) {
        super(message);
    }
}
