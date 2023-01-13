package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.exceptions.IndexAlreadyExistsException;
import com.musicdatabase.service.controller.viewmodel.SongViewModel;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.JsonDataWriter;
import com.musicdatabase.service.repository.SongRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Profile({"JPA", "JDBC", "collections"})
public class SongServiceImpl implements SongService {

    private final JsonDataWriter jsonDataWriter;
    private final SongRepository songRepository;

    private final Logger logger = Logger.getLogger(SongServiceImpl.class.getName());

    public SongServiceImpl(SongRepository songRepository, JsonDataWriter jsonDataWriter) {
        this.songRepository = songRepository;
        this.jsonDataWriter = jsonDataWriter;
    }

    @Override
    public List<Song> getSongs() {
        logger.info("getSongs called");
        return songRepository.readSongs();
    }

    @Override
    public Song getSong(String title) {
        logger.info("getSong called with title: " + title);
        return songRepository.readSongs().stream().filter(song -> song.getTitle().equals(title)).findFirst().orElse(null);
    }

    @Override
    public List<Song> findSongsByAuthorName(String author) {
        logger.info("readSongsByAuthor called with author: " + author);
        return songRepository.readSongs().stream()
                .filter(song -> song.getAuthors().stream().anyMatch(songAuthor -> songAuthor.getName().equals(author)))
                .toList();
    }

    @Override
    public void createSong(Song song) {
        logger.info("createSong called with song: " + song);
        if (song.getAlbum().getSongIndexes().stream().anyMatch(songIndex -> songIndex == song.getIndex())) {
            throw new IndexAlreadyExistsException("Index already exists");
        }
        songRepository.createSong(song);
    }

    @Override
    public void removeSong(Song song) {
        logger.info("removeSong called with song: " + song);
        songRepository.deleteSong(song);
    }

    @Override
    public void updateSong(Song originalSong, Song newSong) {
        logger.info("updateSong called with originalSong: " + originalSong + " and newSong: " + newSong);
        songRepository.updateSong(originalSong, newSong);
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

    @Override
    public void writeSongsToJSON(SongRepository song) {
        logger.info("writeSongsToJSON called");
        jsonDataWriter.writeSongs(song);

    }

    @Override
    public List<Song> findSongsByAlbumName(String albumName) {
        logger.info("findSongsByAlbumName called with albumName: " + albumName);
        return songRepository.findSongByAlbumName(albumName);
    }

}
