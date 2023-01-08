package com.musicdatabase.service.service;

import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.SongRepository;

import java.util.List;

public interface SongService {

    List<Song> getSongs();

    Song getSong(String title);

    List<Song> findSongsByAuthorName(String author);

    List<Song> findSongsByAlbumName(String album);


    void createSong(Song song);

    void removeSong(Song song);

    void updateSong(Song originalSong, Song newSong);

    void writeSongsToJSON(SongRepository songs);
}
