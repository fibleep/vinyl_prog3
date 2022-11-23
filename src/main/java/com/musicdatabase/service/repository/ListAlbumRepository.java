package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListAlbumRepository implements AlbumRepository {
    static final List<Album> albums= new ArrayList<>();
    @Override
    public Album createAlbum(Album album) {
        albums.add(album);
        return album;
    }

    @Override
    public void deleteAlbum(Album album) {
        albums.remove(album);
    }

    @Override
    public List<Album> readAlbums() {
        return albums;
    }
}
