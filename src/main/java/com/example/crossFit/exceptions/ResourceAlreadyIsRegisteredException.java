package com.example.crossFit.exceptions;

import java.util.function.Supplier;

public class ResourceAlreadyIsRegisteredException extends RuntimeException implements Supplier<ResourceAlreadyIsRegisteredException> {

    public ResourceAlreadyIsRegisteredException() {
    }

    public ResourceAlreadyIsRegisteredException(String message) {
        super(message);
    }


    @Override
    public ResourceAlreadyIsRegisteredException get() {
        return new ResourceAlreadyIsRegisteredException(getMessage());
    }
}
