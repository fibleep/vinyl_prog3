package com.musicdatabase.service.repository;

import com.musicdatabase.service.domain.Author;

import java.util.ArrayList;
import java.util.List;

public class ListAuthorRepository implements AuthorRepository {

private List<Author> authors = new ArrayList<>();

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
