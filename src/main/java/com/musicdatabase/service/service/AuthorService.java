package com.musicdatabase.service.service;

import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.repository.AuthorRepository;

import java.util.List;

public interface AuthorService {
    List<Author> readAuthors();

    void writeAuthorsToJSON(AuthorRepository authors);
}
