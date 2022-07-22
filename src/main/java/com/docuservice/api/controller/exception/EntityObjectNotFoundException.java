package com.docuservice.api.controller.exception;

public class EntityObjectNotFoundException extends RuntimeException {

    public EntityObjectNotFoundException(String message) {
        super(message);
    }

    public EntityObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
