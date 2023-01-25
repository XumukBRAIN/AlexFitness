package com.example.crossFit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityAlreadyIsRegisteredException extends ResponseStatusException {
    public EntityAlreadyIsRegisteredException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
