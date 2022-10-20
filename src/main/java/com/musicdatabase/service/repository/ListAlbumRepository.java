package com.musicdatabase.service.repository;

import com.musicdatabase.service.domain.Album;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListAlbumRepository implements AlbumRepository {
    private List<Album> albums= new ArrayList<>();
    @Override
    public Album createAlbum(Album album) {
        albums.add(album);
        return album;
    }

    @Override
    public List<Album> readAlbums() {
        return albums;
    }
}
