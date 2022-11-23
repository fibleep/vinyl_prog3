package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;

import java.util.List;

public interface AlbumRepository {
    Album createAlbum(Album album);
    List<Album> readAlbums();

    void deleteAlbum(Album album);
}
