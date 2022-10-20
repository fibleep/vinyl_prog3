package com.musicdatabase.service.service;

import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.repository.AuthorRepository;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final JsonDataWriter jsonDataWriter;

    public AuthorServiceImpl(AuthorRepository authorRepository, JsonDataWriter jsonDataWriter) {
        this.authorRepository = authorRepository;
        this.jsonDataWriter = jsonDataWriter;
    }

    @Override
    public List<Author> readAuthors() {
        return authorRepository.readAuthors();
    }

    @Override
    public void writeAuthorsToJSON(AuthorRepository authors) {
        jsonDataWriter.writeAuthors(authors);
    }
}
