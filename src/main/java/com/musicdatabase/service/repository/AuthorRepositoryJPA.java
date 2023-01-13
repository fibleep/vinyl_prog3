package com.musicdatabase.service.repository;

import com.musicdatabase.service.controller.AlbumController;
import com.musicdatabase.service.controller.exceptions.DatabaseException;
import com.musicdatabase.service.model.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Profile("JPA")
@Transactional
public class AuthorRepositoryJPA implements AuthorRepository {
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> readAuthors() {
        logger.info("Reading authors from database");
        try {
            return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Author createAuthor(Author author) {
        logger.info("Creating author in database");
        try {
            entityManager.persist(author);
            return author;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void removeAuthor(Author author) {
        logger.info("Removing author from database");
        try {
            entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {
        logger.info("Updating author in database");
        try {
            author.setName(newAuthor.getName());
            author.setGender(newAuthor.getGender());
            author.setName(newAuthor.getName());
            author.setAge(newAuthor.getAge());
            entityManager.merge(author);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Author> findAuthorBySongTitle(String title) {
        logger.info("Finding author by song title in database");
        try {
            return null;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Author findAuthorByAlbumName(String album) {
        logger.info("Finding author by album name in database");
        return entityManager.createQuery("Select author from Author author where author.name = (SELECT a.author.name FROM Album a WHERE a.name = :album)", Author.class)
                .setParameter("album", album)
                .getSingleResult();
    }

}
