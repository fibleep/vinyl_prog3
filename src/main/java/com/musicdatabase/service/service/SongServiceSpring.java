package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.SongViewModel;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.SongRepositorySpring;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Profile("spring")
public class SongServiceSpring implements SongService {
    private final SongRepositorySpring songRepository;
    private final Logger logger = Logger.getLogger(SongServiceImpl.class.getName());

    public SongServiceSpring(SongRepositorySpring songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getSongs() {
        logger.info("getSongs called");
        return songRepository.findAll();
    }

    @Override
    public Song getSong(String title) {
        logger.info("getSong called with title: " + title);
        return songRepository.findByTitle(title);
    }

    @Override
    public List<Song> findSongsByAuthorName(String author) {
        return songRepository.findAll().parallelStream().filter(song -> song.getAuthors().stream().anyMatch(s -> s.getName().equals(author))).toList();
    }

    @Override
    public List<Song> findSongsByAlbumName(String album) {
        logger.info("findSongsByAlbumName called with album: " + album);
        return songRepository.findByAlbumName(album);
    }

    @Override
    public void createSong(Song song) {
        logger.info("createSong called with song: " + song);
        songRepository.save(song);
    }

    @Override
    public void removeSong(Song song) {
        logger.info("removeSong called with song: " + song);
        songRepository.delete(song);
    }

    @Override
    public void updateSong(Song originalSong, Song newSong) {
        logger.info("updateSong called with originalSong: " + originalSong + " and newSong: " + newSong);
        songRepository.save(newSong);
    }

    @Override
    public Song merge(Song originalSong, SongViewModel song) {
        if (song.getTitle() != null && !song.getTitle().isEmpty()) {
            originalSong.setTitle(song.getTitle());
        }
        if (song.getLength() != 0.0) {
            originalSong.setLength(song.getLength());
        }
        return originalSong;
    }
}
