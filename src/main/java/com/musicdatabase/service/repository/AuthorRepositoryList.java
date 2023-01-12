package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("collections")
@Repository
public class AuthorRepositoryList implements AuthorRepository {

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

    @Override
    public List<Author> findAuthorBySongTitle(String title) {
        return null;
    }

    @Override
    public Author findAuthorByAlbumName(String album) {
        return authors.stream()
                .filter(author -> author.getAlbums().stream()
                        .anyMatch(album1 -> album1.getName().equals(album)))
                .findFirst()
                .orElse(null);
    }
}
