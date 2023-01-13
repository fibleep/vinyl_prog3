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
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Album createAlbum(Album album) {
        entityManager.persist(album);
        return album;
    }

    @Override
    public List<Album> readAlbums() throws DatabaseException {
        try {
            return entityManager.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void deleteAlbum(Album album) {
        try {
            entityManager.remove(album);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void updateAlbum(Album originalAlbum, Album newAlbum) {
        try {
            originalAlbum.setName(newAlbum.getName());
            originalAlbum.setYear(LocalDate.of(newAlbum.getYear(), 1, 1).atStartOfDay());
            originalAlbum.setGenre(newAlbum.getGenre());
            originalAlbum.setSongs(newAlbum.getSongs());
            originalAlbum.setAuthor(newAlbum.getAuthor());
            entityManager.merge(originalAlbum);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Album findAlbumByName(String name) {
        try {
            return entityManager.createQuery("SELECT a FROM Album a WHERE a.name = :name", Album.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
