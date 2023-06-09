package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.AuthorViewModel;
import com.musicdatabase.service.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    Author getAuthorByName(String name);

    void addAuthor(Author author);

    void updateAuthor(Author originalAuthor, Author newAuthor);

    Author merge(Author originalAuthor, AuthorViewModel authorViewModel);

    void removeAuthor(Author author);
}
