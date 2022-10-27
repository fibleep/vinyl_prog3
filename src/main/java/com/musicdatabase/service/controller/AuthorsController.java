package com.musicdatabase.service.controller;

import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping("/addauthor")
    public ModelAndView showAddAuthor() {
        logger.info("addAuthor called");
        return new ModelAndView("addauthor");
    }
    @PostMapping("/addauthor")
    public ModelAndView processAddAuthor(String name, String gender,int age) {
        logger.info("addAuthor called");
        authorService.addAuthor(new Author(name,age,gender,null,null));
        return new ModelAndView("redirect:/authors");
    }
    @GetMapping
    public ModelAndView getAuthors() {
        logger.info("getAuthors called");
        return new ModelAndView("authors", "authors", authorService.getAuthors());
    }
}
