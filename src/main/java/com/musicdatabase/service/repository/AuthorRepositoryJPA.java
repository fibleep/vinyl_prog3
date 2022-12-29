package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("JPA")
public class AuthorRepositoryJPA implements AuthorRepository {
    @Override
    public List<Author> readAuthors() {
        return null;
    }

    @Override
    public Author createAuthor(Author author) {
        return null;
    }

    @Override
    public void removeAuthor(Author author) {

    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {

    }
}
