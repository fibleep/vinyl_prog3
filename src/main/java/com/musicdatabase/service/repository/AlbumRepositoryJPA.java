package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("JPA")
public class AlbumRepositoryJPA implements AlbumRepository {
    @Override
    public Album createAlbum(Album album) {
        return null;
    }

    @Override
    public List<Album> readAlbums() {
        return null;
    }

    @Override
    public void deleteAlbum(Album album) {

    }

    @Override
    public void updateAlbum(Album originalAlbum, Album newAlbum) {

    }
}
