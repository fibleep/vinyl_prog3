package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.AlbumViewModel;
import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Song;

import java.util.List;

public interface AlbumService {
    List<Album> getAlbums();

    List<Album> readAlbumsByAuthorAndYear(String title, int year);

    void addAlbum(Album album);

    void removeAlbum(Album album);

    void updateAlbum(Album originalAlbum, Album newAlbum);

    Album merge(Album originalAlbum, AlbumViewModel albumViewModel);

    List<Song> getAlbumSongs(String albumName);
}
