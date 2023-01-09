package com.musicdatabase.service.repository;

import com.musicdatabase.service.controller.AlbumController;
import com.musicdatabase.service.controller.exceptions.DatabaseException;
import com.musicdatabase.service.model.Song;
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
public class SongRepositoryJPA implements SongRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());

    @Override
    public List<Song> readSongs() {
        try {
            return entityManager.createQuery("from Song", Song.class).getResultList();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Song createSong(Song song) {
        entityManager.persist(song);
        return song;
    }

    public List<Song> findSongByAlbumName(String albumName) {
        return entityManager.createQuery("SELECT s FROM Song s WHERE s.album.name = :albumName", Song.class)
                .setParameter("albumName", albumName)
                .getResultList();
    }

    @Override
    public void deleteSong(Song song) {
        entityManager.remove(song);
    }

    @Override
    public void updateSong(Song song, Song newSong) {
        entityManager.merge(newSong);
    }
}
