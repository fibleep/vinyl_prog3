package com.musicdatabase.service.service;

import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.repository.AlbumRepository;

import java.util.List;

public interface AlbumService {
    List<Album> getAlbums();
    List<Album> readAlbumsByAuthor(String author);
    List<Album> readAlbumsByAuthorAndYear(String title, int year);

    void addAlbum(Album album);
    void removeAlbum(Album album);

    void writeAlbumsToJSON(AlbumRepository albums);
}
