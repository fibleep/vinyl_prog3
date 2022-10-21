package com.musicdatabase.service.controller;

import com.musicdatabase.service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private Logger logger = Logger.getLogger(AuthorsController.class.getName());
    @Autowired
    private AuthorService authorService;
    public AuthorsController(AuthorService authorService) {
        logger.info("AuthorsController created");
        this.authorService = authorService;
    }
    @GetMapping
    public ModelAndView getAuthors() {
        logger.info("getAuthors called");
        return new ModelAndView("authors", "authors", authorService.getAuthors());
    }
}
