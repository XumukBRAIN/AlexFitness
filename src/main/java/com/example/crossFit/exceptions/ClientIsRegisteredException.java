package com.example.crossFit.exceptions;

public class ClientIsRegisteredException extends Exception {

    public ClientIsRegisteredException() {
    }

    public ClientIsRegisteredException(String message) {
        super(message);
    }

    public ClientIsRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientIsRegisteredException(Throwable cause) {
        super(cause);
    }

    public ClientIsRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
