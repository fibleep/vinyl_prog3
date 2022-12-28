package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("collections")
@Repository
public class ListAuthorRepository implements AuthorRepository {

    static final List<Author> authors = new ArrayList<>();

    @Override
    public List<Author> readAuthors() {
        return authors;
    }

    @Override
    public Author createAuthor(Author author) {
        authors.add(author);
        return author;
    }

    @Override
    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {
        authors.set(authors.indexOf(author), newAuthor);
    }
}
