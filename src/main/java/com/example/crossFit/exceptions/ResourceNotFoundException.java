package com.example.crossFit.exceptions;

import java.util.function.Supplier;

public class ResourceNotFoundException extends RuntimeException implements Supplier<ResourceNotFoundException> {

    public ResourceNotFoundException(String message) {
        super(message);
    }


    @Override
    public ResourceNotFoundException get() {
        return new ResourceNotFoundException(getMessage());
    }
}
