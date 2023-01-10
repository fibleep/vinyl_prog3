package com.musicdatabase.service.service;

import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.JsonDataWriter;
import com.musicdatabase.service.repository.SongRepository;
import com.musicdatabase.service.repository.SongRepositorySpring;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Profile("spring")
public class SongServiceSpring implements SongService {
    private final JsonDataWriter jsonDataWriter;
    private final SongRepositorySpring songRepository;

    public SongServiceSpring(SongRepositorySpring songRepository, JsonDataWriter jsonDataWriter) {
        this.songRepository = songRepository;
        this.jsonDataWriter = jsonDataWriter;
    }

    private final Logger logger = Logger.getLogger(SongServiceImpl.class.getName());

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
        logger.info("findSongsByAuthorName called with author: " + author);
        return songRepository.findByAuthor(author);
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
    }

    @Override
    public void writeSongsToJSON(SongRepository songs) {

    }
}
