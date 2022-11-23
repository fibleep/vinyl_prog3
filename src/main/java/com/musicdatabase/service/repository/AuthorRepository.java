package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> readAuthors();
    Author createAuthor(Author author);
}
