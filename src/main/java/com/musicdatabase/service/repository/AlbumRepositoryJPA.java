package com.musicdatabase.service.repository;

import com.musicdatabase.service.controller.AlbumController;
import com.musicdatabase.service.controller.exceptions.DatabaseException;
import com.musicdatabase.service.model.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Profile("JPA")
@Transactional
public class AlbumRepositoryJPA implements AlbumRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());

    @Override
    public Album createAlbum(Album album) {
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        return album;
    }

    @Override
    public List<Album> readAlbums() throws DatabaseException {
        try {
            return entityManager.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void deleteAlbum(Album album) {
        entityManager.getTransaction().begin();
        entityManager.remove(album);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAlbum(Album originalAlbum, Album newAlbum) {
        entityManager.getTransaction().begin();
        originalAlbum.setName(newAlbum.getName());
        originalAlbum.setYear(LocalDate.of(newAlbum.getYear(), 1, 1).atStartOfDay());
        originalAlbum.setGenre(newAlbum.getGenre());
        entityManager.getTransaction().commit();
    }
}
