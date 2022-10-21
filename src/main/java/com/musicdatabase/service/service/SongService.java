package com.musicdatabase.service.service;

import com.musicdatabase.service.domain.Song;
import com.musicdatabase.service.repository.SongRepository;

import java.util.List;

public interface SongService {

    List<Song> getSongs();
    List<Song> readSongsByAuthor(String author);

    void writeSongsToJSON(SongRepository songs);
}
