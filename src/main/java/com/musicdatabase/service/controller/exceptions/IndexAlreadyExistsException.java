package com.musicdatabase.service.controller.exceptions;

public class IndexAlreadyExistsException extends RuntimeException {
    public IndexAlreadyExistsException(String message) {
        super(message);
    }
}
