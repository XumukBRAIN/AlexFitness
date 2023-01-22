package com.example.crossFit.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityAlreadyIsRegistered extends ResponseStatusException {
    public EntityAlreadyIsRegistered(HttpStatus status, String reason) {
        super(status, reason);
    }
}
