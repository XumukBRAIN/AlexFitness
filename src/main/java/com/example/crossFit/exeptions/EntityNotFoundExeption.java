package com.example.crossFit.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundExeption extends ResponseStatusException {

    public EntityNotFoundExeption(HttpStatus status, String reason) {
        super(status, reason);
    }
}
