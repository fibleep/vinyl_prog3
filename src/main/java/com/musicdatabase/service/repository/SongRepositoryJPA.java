package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Song;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("JPA")
public class SongRepositoryJPA implements SongRepository {
    @Override
    public List<Song> readSongs() {
        return null;
    }

    @Override
    public Song getSong(String title) {
        return null;
    }

    @Override
    public Song createSong(Song song) {
        return null;
    }

    @Override
    public void deleteSong(Song song) {

    }

    @Override
    public void updateSong(Song song, Song newSong) {

    }
}
