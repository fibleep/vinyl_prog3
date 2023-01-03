package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.exceptions.DatabaseException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@ControllerAdvice
public class ErrorController {
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());

    @RequestMapping("/error")
    public ModelAndView error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        logger.info("ErrorController called");
        ModelAndView modelAndView = new ModelAndView("error");
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                modelAndView.addObject("error", "404");
                modelAndView.addObject("message", "Page not found");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                modelAndView.addObject("error", "500");
                modelAndView.addObject("message", "Internal server error");
            } else {
                modelAndView.addObject("error", "Unknown error");
                modelAndView.addObject("message", "Unknown error");
            }
        }
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView("error");
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        modelAndView.addObject("error", "Unknown error");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleDatabaseException(Exception ex) throws Exception {
        logger.info("DatabaseExceptionHandler called");
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", "Database error");
        modelAndView.addObject("message", ex);
        return modelAndView;
    }
}
