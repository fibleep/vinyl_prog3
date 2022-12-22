package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.converter.StringToGenderConverter;
import com.musicdatabase.service.controller.viewmodel.AuthorViewModel;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.session.PageVisit;
import com.musicdatabase.service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
    public ModelAndView showAddAuthor(Model model, HttpServletRequest request) {
        logger.info("addAuthor called");
        model.addAttribute("authorViewModel", new AuthorViewModel());
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/author/addauthor");
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
        return new ModelAndView("redirect:/author/authors");
    }

    @GetMapping("/{author}")
    public ModelAndView showAuthor(@PathVariable String author, HttpServletRequest request) {
        logger.info("showAuthor called");
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/author/author-details", "author", authorService.getAuthors().stream().filter(a -> a.getName().equals(author)).findFirst().get());
    }

    @GetMapping
    public ModelAndView getAuthors(HttpServletRequest request) {
        logger.info("getAuthors called");
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/author/authors", "authors", authorService.getAuthors());
    }
}
