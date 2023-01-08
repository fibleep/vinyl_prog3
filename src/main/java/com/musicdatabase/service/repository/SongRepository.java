package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Song;

import java.util.List;

public interface SongRepository {
    List<Song> readSongs();

    Song getSong(String title);

    Song createSong(Song song);

    void deleteSong(Song song);

    void updateSong(Song song, Song newSong);

    List<Song> findSongByAlbumName(String albumName);
}
