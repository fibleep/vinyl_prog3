package com.musicdatabase.service.service;

import com.musicdatabase.service.domain.Song;
import com.musicdatabase.service.repository.JsonDataWriter;
import com.musicdatabase.service.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
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
        return songRepository.readSongs();
    }

    @Override
    public List<Song> readSongsByAuthor(String author) {
        logger.info("readSongsByAuthor called");
        return songRepository.readSongs().stream()
                .filter(song -> song.getAuthors().stream().anyMatch(songAuthor -> songAuthor.getName().equals(author))).toList();
    }

    @Override
    public void addSong(Song song) {
        songRepository.createSong(song);
    }
    @Override
    public void removeSong(Song song) {
        songRepository.deleteSong(song);
    }

    @Override
    public void writeSongsToJSON(SongRepository song) {
        logger.info("writeSongsToJSON called");
        jsonDataWriter.writeSongs(song);

    }
}
