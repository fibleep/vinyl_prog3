package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Profile("JPA")
public class AuthorRepositoryJPA implements AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> readAuthors() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Override
    public Author createAuthor(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
        return author;
    }

    @Override
    public void removeAuthor(Author author) {
        entityManager.getTransaction().begin();
        entityManager.remove(author);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {
        entityManager.getTransaction().begin();
        // TODO: mmmm prototype design pattern?
        author.setName(newAuthor.getName());
        author.setGender(newAuthor.getGender());
        author.setAlbums(newAuthor.getAlbums());
        author.setName(newAuthor.getName());
        author.setSongs(newAuthor.getSongs());
        author.setAge(newAuthor.getAge());
        entityManager.getTransaction().commit();
    }
}
