package com.musicdatabase.service.service;

import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.repository.AuthorRepository;
import com.musicdatabase.service.repository.JsonDataWriter;
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
    public List<Author> getAuthors() {
        return authorRepository.readAuthors();
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.createAuthor(author);
    }

    @Override
    public void writeAuthorsToJSON(AuthorRepository authors) {
        jsonDataWriter.writeAuthors(authors);
    }
}
