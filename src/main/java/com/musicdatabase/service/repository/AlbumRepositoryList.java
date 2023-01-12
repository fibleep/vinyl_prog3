package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("collections")
@Repository
public class AlbumRepositoryList implements AlbumRepository {
    static final List<Album> albums = new ArrayList<>();

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
    public void updateAlbum(Album album, Album newAlbum) {
        albums.set(albums.indexOf(album), newAlbum);
    }

    @Override
    public Album findAlbumBySongTitle(String title) {
        return null;
    }

    @Override
    public Album findAlbumByName(String name) {
        return albums.stream().filter(album -> album.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Album> readAlbums() {
        return albums;
    }
}
