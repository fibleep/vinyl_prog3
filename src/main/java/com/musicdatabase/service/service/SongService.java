package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.SongViewModel;
import com.musicdatabase.service.model.Song;

import java.util.List;

public interface SongService {

    List<Song> getSongs();

    Song getSong(String title);

    List<Song> findSongsByAuthorName(String author);

    List<Song> findSongsByAlbumName(String album);


    void createSong(Song song);

    void removeSong(Song song);

    void updateSong(Song originalSong, Song newSong);

    Song merge(Song originalSong, SongViewModel song);
}
