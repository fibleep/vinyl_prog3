package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("JPA")
public class AlbumRepositoryJPA implements AlbumRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Album createAlbum(Album album) {
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        return album;
    }

    @Override
    public List<Album> readAlbums() {
        return entityManager.createQuery("SELECT a FROM Album a", Album.class).getResultList();
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
        originalAlbum.setAuthor(newAlbum.getAuthor());
        originalAlbum.setSongs(newAlbum.getSongs());
        entityManager.getTransaction().commit();
    }
}
