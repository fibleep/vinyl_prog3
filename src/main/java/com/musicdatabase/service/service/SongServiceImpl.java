package com.musicdatabase.service.service;

import com.musicdatabase.service.domain.Song;
import com.musicdatabase.service.repository.JsonDataWriter;
import com.musicdatabase.service.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final JsonDataWriter jsonDataWriter;
    private final SongRepository songRepository;

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
        return songRepository.readSongs().stream()
                .filter(song -> song.getAuthors().stream().anyMatch(songAuthor -> songAuthor.getName().equals(author))).toList();
    }

    @Override
    public void writeSongsToJSON(SongRepository song) {
        jsonDataWriter.writeSongs(song);

    }
}
