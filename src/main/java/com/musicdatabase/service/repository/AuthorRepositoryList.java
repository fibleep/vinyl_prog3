package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Profile("collections")
@Repository
public class AuthorRepositoryList implements AuthorRepository {

    static final List<Author> authors = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AuthorRepositoryList.class.getName());

    @Override
    public List<Author> readAuthors() {
        logger.info("Reading authors from database");
        return authors;
    }

    @Override
    public Author createAuthor(Author author) {
        logger.info("Creating author in database");
        authors.add(author);
        return author;
    }

    @Override
    public void removeAuthor(Author author) {
        logger.info("Removing author from database");
        authors.remove(author);
    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {
        logger.info("Updating author in database");
        authors.set(authors.indexOf(author), newAuthor);
    }

    @Override
    public List<Author> findAuthorBySongTitle(String title) {
        return null;
    }

    @Override
    public Author findAuthorByAlbumName(String album) {
        logger.info("Finding author by album name");
        return authors.stream()
                .filter(author -> author.getAlbums().stream()
                        .anyMatch(album1 -> album1.getName().equals(album)))
                .findFirst()
                .orElse(null);
    }
}
