package com.musicdatabase.service.service;

import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.SongRepository;

import java.util.List;

public interface SongService {

    List<Song> getSongs();
    List<Song> readSongsByAuthor(String author);

    void addSong(Song song);
    void removeSong(Song song);

    void writeSongsToJSON(SongRepository songs);
}
