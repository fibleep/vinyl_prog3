package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.AuthorViewModel;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.repository.AuthorRepository;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    Author getAuthorByName(String name);

    void writeAuthorsToJSON(AuthorRepository authors);

    void addAuthor(Author author);

    void updateAuthor(Author originalAuthor, Author newAuthor);

    void removeAuthor(Author author);

    void mergeAuthorWithModel(Author originalAuthor, AuthorViewModel authorViewModel);
}
