package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.AlbumViewModel;
import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Genre;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.AlbumRepository;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Profile({"JPA", "JDBC", "collections"})
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final JsonDataWriter jsonDataWriter;
    private final SongService songService;
    private final Logger logger = Logger.getLogger(AlbumServiceImpl.class.getName());

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
        logger.info("readAlbumsByAuthor called with author: " + author);
        return albumRepository.readAlbums().stream().filter(album -> album.getAuthor().getName().equals(author)).collect(Collectors.toList());
    }

    @Override
    public void addAlbum(Album album) {
        logger.info("addAlbum called with album: " + album);
        albumRepository.createAlbum(album);
    }

    @Override
    public void removeAlbum(Album album) {
        logger.info("removeAlbum called with album: " + album);
        albumRepository.deleteAlbum(album);
    }

    @Override
    public List<Album> readAlbumsByAuthorAndYear(String name, int year) {
        logger.info("readAlbumsByAuthorAndYear called with name: " + name + " and year: " + year);
        return albumRepository.readAlbums().stream().filter(album -> album.getAuthor().getName().equals(name) && album.getYear() == year).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateAlbum(Album originalAlbum, Album newAlbum) {
        logger.info("updateAlbum called with originalAlbum: " + originalAlbum + " and newAlbum: " + newAlbum);
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

    @Override
    public Album merge(Album originalAlbum, AlbumViewModel albumViewModel) {
        logger.info("merge called with originalAlbum: " + originalAlbum + " and albumViewModel: " + albumViewModel);
        originalAlbum.setName(albumViewModel.getName());
        LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(albumViewModel.getYear()), 1, 1, 0, 0);
        originalAlbum.setYear(localDateTime);
        originalAlbum.setGenre(Genre.valueOf(albumViewModel.getGenre()));
        return originalAlbum;
    }

    @Override
    public Album getAlbumByName(String name) {
        logger.info("getAlbumByName called with name: " + name);
        return albumRepository.findAlbumByName(name);
    }

    @Override
    public List<Song> getAlbumSongs(String albumName) {
        logger.info("getAlbumSongs called with albumName: " + albumName);
        return songService.findSongsByAlbumName(albumName);
    }
}
