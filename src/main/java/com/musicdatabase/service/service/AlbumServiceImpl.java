package com.musicdatabase.service.service;

import com.musicdatabase.service.domain.Album;
import com.musicdatabase.service.repository.AlbumRepository;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final JsonDataWriter jsonDataWriter;

    public AlbumServiceImpl(AlbumRepository albumRepository, JsonDataWriter jsonDataWriter) {
        this.albumRepository = albumRepository;
        this.jsonDataWriter = jsonDataWriter;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.readAlbums();
    }

    @Override
    public List<Album> readAlbumsByAuthor(String author) {
        // TODO implement me plz
        return null;
    }

    @Override
    public List<Album> readAlbumsByAuthorAndYear(String name, int year) {
        return albumRepository.readAlbums().stream().filter(album -> album.getArtist().getName().equals(name) && album.getYear().getYear() == year).collect(Collectors.toList());
    }

    @Override
    public void writeAlbumsToJSON(AlbumRepository album) {
        jsonDataWriter.writeAlbums(album);
    }
}
