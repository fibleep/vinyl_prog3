package com.musicdatabase.service.service;

import com.musicdatabase.service.domain.Album;
import com.musicdatabase.service.repository.AlbumRepository;

import java.util.List;

public interface AlbumService {
    List<Album> readAlbums();
    List<Album> readAlbumsByAuthor(String author);
    List<Album> readAlbumsByAuthorAndYear(String title, int year);

    void writeAlbumsToJSON(AlbumRepository albums);
}
