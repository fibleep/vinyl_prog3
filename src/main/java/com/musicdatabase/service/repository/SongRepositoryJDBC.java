package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("JDBC")
public class SongRepositoryJDBC implements SongRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Song> readSongs() {
        return jdbcTemplate.query("SELECT * FROM song", (resultSet, i) -> {
            Song song = new Song();
            song.setTitle(resultSet.getString("title"));
            // TODO: PLS FIX
            song.setAuthors(null);
            song.setLength(resultSet.getInt("duration"));
            song.setIndex(resultSet.getInt("album_index"));
            return song;
        });
    }

    @Override
    public Song getSong(String title) {
        return null;
    }

    @Override
    public Song createSong(Song song) {
        return null;
    }

    @Override
    public void deleteSong(Song song) {

    }

    @Override
    public void updateSong(Song song, Song newSong) {

    }

    @Override
    public List<Song> findSongByAlbumName(String albumName) {
        return null;
    }
}