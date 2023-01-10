package com.musicdatabase.service.service;

import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.AlbumRepository;
import com.musicdatabase.service.repository.AlbumRepositorySpring;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Profile("spring")
public class AlbumServiceSpring implements AlbumService {
    private final AlbumRepositorySpring albumRepository;
    private final JsonDataWriter jsonDataWriter;
    private final SongService songService;
    private final Logger logger = Logger.getLogger(AlbumServiceImpl.class.getName());

    public AlbumServiceSpring(AlbumRepositorySpring albumRepository, SongService songService, JsonDataWriter jsonDataWriter) {
        this.albumRepository = albumRepository;
        this.songService = songService;
        this.jsonDataWriter = jsonDataWriter;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public List<Album> readAlbumsByAuthor(String author) {
        logger.info("readAlbumsByAuthor called");
        return albumRepository.findByName(author);
    }

    @Override
    public List<Album> readAlbumsByAuthorAndYear(String title, int year) {
        return albumRepository.findByNameAndYear(title, year);
    }

    @Override
    public void addAlbum(Album album) {
        albumRepository.save(album);
    }

    @Override
    public void removeAlbum(Album album) {
        album.getSongs().forEach(songService::removeSong);
        albumRepository.delete(album);
    }

    @Override
    public void updateAlbum(Album originalAlbum, Album newAlbum) {
        albumRepository.save(newAlbum);
    }

    @Override
    public void writeAlbumsToJSON(AlbumRepository albums) {

    }

    @Override
    public List<Song> getAlbumSongs(String albumName) {
        return songService.findSongsByAlbumName(albumName);
    }
}