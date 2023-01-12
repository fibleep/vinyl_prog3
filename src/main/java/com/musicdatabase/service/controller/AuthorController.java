package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.converter.StringToGenderConverter;
import com.musicdatabase.service.controller.viewmodel.AuthorViewModel;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Gender;
import com.musicdatabase.service.model.session.PageVisit;
import com.musicdatabase.service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final Logger logger = Logger.getLogger(AuthorController.class.getName());
    @Autowired
    private AuthorService authorService;
    @Autowired
    private HistoryController historyController;

    public AuthorController(AuthorService authorService, HistoryController historyController) {
        logger.info("AuthorsController created");
        this.authorService = authorService;
        this.historyController = historyController;
    }

    @GetMapping("/addauthor")
    public ModelAndView showAddAuthor(HttpServletRequest request) {
        logger.info("addAuthor called");
        ModelAndView modelAndView = new ModelAndView("/author/addauthor");
        modelAndView.addObject("authorViewModel", new AuthorViewModel());
        modelAndView.addObject("genders", Gender.values());
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return modelAndView;
    }

    @PostMapping("/addauthor")
    public ModelAndView processAddAuthor(@Valid @ModelAttribute("authorViewModel") AuthorViewModel authorViewModel, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("addAuthor called");
        StringToGenderConverter stringToGenderConverter = new StringToGenderConverter();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.warning(error.toString()));
            return new ModelAndView("/author/addauthor");
        }
        Author author = new Author();
        author.setName(authorViewModel.getName());
        author.setGender(stringToGenderConverter.convert(authorViewModel.getGender()));
        author.setAge(authorViewModel.getAge());
        authorService.addAuthor(author);
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("redirect:/authors");
    }

    @GetMapping("/{author}")
    public ModelAndView showAuthor(@PathVariable String author, HttpServletRequest request) {
        logger.info("showAuthor called");
        ModelAndView modelAndView = new ModelAndView("/author/author-details");
        modelAndView.addObject("author", authorService.getAuthorByName(author));
        modelAndView.addObject("authorViewModel", new AuthorViewModel());
        modelAndView.addObject("genders", Gender.values());
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return modelAndView;
    }

    @PutMapping("/{author}")
    public ModelAndView updateAuthor(@PathVariable String author, @Valid @ModelAttribute("authorViewModel") AuthorViewModel authorViewModel, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("updateAuthor called");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.warning(error.toString()));
            return new ModelAndView("/author/author-details");
        }
        Author originalAuthor = authorService.getAuthorByName(author);
        Author newAuthor = originalAuthor;
        authorService.mergeAuthorWithModel(originalAuthor, authorViewModel);
        authorService.updateAuthor(originalAuthor, newAuthor);
        return new ModelAndView("redirect:/authors");
    }

    @DeleteMapping("/{author}")
    public ModelAndView deleteAuthor(@PathVariable String author, HttpServletRequest request) {
        logger.info("deleteAuthor called");
        authorService.removeAuthor(authorService.getAuthorByName(author));
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("redirect:/authors");
    }

    @GetMapping
    public ModelAndView getAuthors(HttpServletRequest request) {
        logger.info("getAuthors called");
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/author/authors", "authors", authorService.getAuthors());
    }
}
