package com.musicdatabase.service.repository;

import com.musicdatabase.service.domain.Song;

import java.util.ArrayList;
import java.util.List;

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


}
