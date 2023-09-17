package com.Daniel.helpdesk.service.exception;

public class ObjectNotFoundException extends RuntimeException {
    private static final long SerialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
