package com.musicdatabase.service.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Database error")
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
