package com.musicdatabase.service.repository;

import com.musicdatabase.service.domain.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> readAuthors();
    Author createAuthor(Author author);
}
