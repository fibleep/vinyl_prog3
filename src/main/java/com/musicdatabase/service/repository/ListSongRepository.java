package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListSongRepository implements SongRepository {
    static final List<Song> songs = new ArrayList<>();

    @Override
    public List<Song> readSongs() {
        return songs;
    }

    @Override
    public Song createSong(Song song) {
        songs.add(song);
        return song;
    }

    @Override
    public void deleteSong(Song song) {
        songs.remove(song);
    }


}
