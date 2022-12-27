package com.musicdatabase.service.service;

import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.AlbumRepository;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final JsonDataWriter jsonDataWriter;
    private final SongService songService;
    private final Logger logger = Logger.getLogger(AlbumServiceImpl.class.getName());

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, SongService songService, JsonDataWriter jsonDataWriter) {
        this.albumRepository = albumRepository;
        this.songService = songService;
        this.jsonDataWriter = jsonDataWriter;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.readAlbums();
    }

    @Override
    public List<Album> readAlbumsByAuthor(String author) {
        // TODO implement me plz
        logger.info("readAlbumsByAuthor called");
        return albumRepository.readAlbums().stream().filter(album -> album.getAuthor().getName().equals(author)).collect(Collectors.toList());
    }

    @Override
    public void addAlbum(Album album) {
        albumRepository.createAlbum(album);
    }

    @Override
    public void removeAlbum(Album album) {
        album.getSongs().forEach(songService::removeSong);
        albumRepository.deleteAlbum(album);
    }

    @Override
    public List<Album> readAlbumsByAuthorAndYear(String name, int year) {
        logger.info("readAlbumsByAuthorAndYear called");
        return albumRepository.readAlbums().stream().filter(album -> album.getAuthor().getName().equals(name) && album.getYear() == year).collect(Collectors.toList());
    }

    @Override
    public void updateAlbum(Album originalAlbum, Album newAlbum) {
        for (Song song : originalAlbum.getSongs()) {
            Song newSong = song;
            newSong.setAlbum(newAlbum);
            songService.updateSong(song, newSong);
        }
        albumRepository.updateAlbum(originalAlbum, newAlbum);
    }

    @Override
    public void writeAlbumsToJSON(AlbumRepository album) {
        logger.info("writeAlbumsToJSON called");
        jsonDataWriter.writeAlbums(album);
    }
}
