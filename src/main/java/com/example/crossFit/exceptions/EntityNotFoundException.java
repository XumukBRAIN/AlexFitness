package com.example.crossFit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

    public EntityNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
