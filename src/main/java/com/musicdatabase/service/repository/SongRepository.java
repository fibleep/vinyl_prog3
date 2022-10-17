package com.musicdatabase.service.repository;

import com.musicdatabase.service.domain.Song;

import java.util.List;

public interface SongRepository {
    List<Song> readSongs();
    Song createSong(Song song);
}
