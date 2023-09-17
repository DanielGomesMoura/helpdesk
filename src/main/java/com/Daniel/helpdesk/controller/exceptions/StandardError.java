package com.Daniel.helpdesk.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.ConstructorParameters;
import java.io.Serializable;

@Data
public class StandardError implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Long timestamp;
    private Integer status;

    private String error;
    private String message;
    private String path;

    public StandardError(Long timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public StandardError() {
    }
}
