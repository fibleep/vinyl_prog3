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
    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());

    @Override
    public List<Author> readAuthors() {
        try {
            return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Author createAuthor(Author author) {
        try {
            entityManager.persist(author);
            return author;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void removeAuthor(Author author) {
        try {
            entityManager.remove(author);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {
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
        try {
            return null;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
