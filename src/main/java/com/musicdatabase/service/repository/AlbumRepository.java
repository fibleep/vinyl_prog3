package com.musicdatabase.service.repository;

import com.musicdatabase.service.domain.Album;

import java.util.List;

public interface AlbumRepository {
    Album createAlbum(Album album);
    List<Album> readAlbums();
}
