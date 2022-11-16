package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.converter.StringToGenderConverter;
import com.musicdatabase.service.controller.viewmodel.AuthorViewModel;
import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private Logger logger = Logger.getLogger(AuthorController.class.getName());
    @Autowired
    private AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        logger.info("AuthorsController created");
        this.authorService = authorService;
    }
    @GetMapping("/addauthor")
    public ModelAndView showAddAuthor(Model model) {
        logger.info("addAuthor called");
        model.addAttribute("authorViewModel", new AuthorViewModel());
        return new ModelAndView("/author/addauthor");
    }
    @PostMapping("/addauthor")
    public ModelAndView processAddAuthor(@Valid @ModelAttribute("authorViewModel") AuthorViewModel authorViewModel, BindingResult bindingResult) {
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
        return new ModelAndView("redirect:/author/authors");
    }
    @GetMapping
    public ModelAndView getAuthors() {
        logger.info("getAuthors called");
        return new ModelAndView("/author/authors", "authors", authorService.getAuthors());
    }
}
