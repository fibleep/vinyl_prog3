package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.AlbumViewModel;
import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Genre;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.AlbumRepositorySpring;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@Profile("spring")
public class AlbumServiceSpring implements AlbumService {
    private final AlbumRepositorySpring albumRepository;
    private final SongService songService;
    private final Logger logger = Logger.getLogger(AlbumServiceImpl.class.getName());

    public AlbumServiceSpring(AlbumRepositorySpring albumRepository, SongService songService) {
        this.albumRepository = albumRepository;
        this.songService = songService;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.findAll();
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
    @Transactional
    public void removeAlbum(Album album) {
        album.getSongs().forEach(songService::removeSong);
        albumRepository.delete(album);
    }

    @Override
    public void updateAlbum(Album originalAlbum, Album newAlbum) {
        albumRepository.save(newAlbum);
    }

    @Override
    public Album merge(Album originalAlbum, AlbumViewModel albumViewModel) {
        logger.info("merge called with originalAlbum: " + originalAlbum + " and albumViewModel: " + albumViewModel.toString());
        originalAlbum.setName(albumViewModel.getName());
        LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(albumViewModel.getYear()), 1, 1, 0, 0);
        originalAlbum.setYear(localDateTime);
        originalAlbum.setGenre(Genre.valueOf(albumViewModel.getGenre()));
        return originalAlbum;
    }

    @Override
    public List<Song> getAlbumSongs(String albumName) {
        return songService.findSongsByAlbumName(albumName);
    }
}
