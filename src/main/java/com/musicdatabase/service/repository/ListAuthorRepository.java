package com.musicdatabase.service.repository;

import com.musicdatabase.service.domain.Author;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
}
