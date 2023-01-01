package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.exceptions.DatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@ControllerAdvice
public class DatabaseExceptionHandler {
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleDatabaseException() {
        logger.info("DatabaseExceptionHandler called");
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }
}
