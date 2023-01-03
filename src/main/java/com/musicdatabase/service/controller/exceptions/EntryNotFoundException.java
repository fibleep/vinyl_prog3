package com.musicdatabase.service.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntryNotFoundException extends Exception {
    public EntryNotFoundException(String message) {
        super(message);
    }
}
