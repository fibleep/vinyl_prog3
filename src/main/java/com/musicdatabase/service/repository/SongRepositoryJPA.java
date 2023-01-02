package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Song;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Profile("JPA")
public class SongRepositoryJPA implements SongRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Song> readSongs() {
        return entityManager.createQuery("SELECT s FROM Song s", Song.class).getResultList();
    }

    @Override
    public Song getSong(String title) {
        return entityManager.createQuery("SELECT s FROM Song s WHERE s.title = :title", Song.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public Song createSong(Song song) {
        entityManager.getTransaction().begin();
        entityManager.persist(song);
        entityManager.getTransaction().commit();
        return song;
    }

    @Override
    public void deleteSong(Song song) {
        entityManager.getTransaction().begin();
        entityManager.remove(song);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateSong(Song song, Song newSong) {
        entityManager.getTransaction().begin();
        song.setTitle(newSong.getTitle());
        song.setAlbum(newSong.getAlbum());
        song.setAuthors(newSong.getAuthors());
        song.setLength(newSong.getLength());
        song.setIndex(newSong.getIndex());
        entityManager.getTransaction().commit();
    }
}
