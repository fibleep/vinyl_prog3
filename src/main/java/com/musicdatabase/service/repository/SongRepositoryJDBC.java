package com.musicdatabase.service.repository;

import com.musicdatabase.service.controller.exceptions.DatabaseException;
import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(SongRepositoryJDBC.class);

    @Override
    public List<Song> readSongs() {
        try {
            return jdbcTemplate.query("SELECT * FROM song", (resultSet, i) -> {
                Song song = new Song();
                song.setTitle(resultSet.getString("title"));
                song.setAuthors(authorRepository.findAuthorBySongTitle(song.getTitle()));
                int albumId = jdbcTemplate.query("select album_id from entry where song_id =?", (resultSet1, i1) -> resultSet1.getInt("album_id"), resultSet.getInt("id")).get(0);

                song.setAlbum(jdbcTemplate.queryForObject("select * from album where id = ?", (resultSet1, i1) -> {
                    Album album = new Album();
                    album.setName(resultSet1.getString("title"));
                    return album;
                }, albumId));
                song.setLength(resultSet.getInt("duration"));
                song.setIndex(resultSet.getInt("album_index"));
                return song;
            });
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    @Override
    public Song createSong(Song song) {
        return null;
    }

    @Override
    public void deleteSong(Song song) {
        try {
            long id = jdbcTemplate.query("select id from song where title = ?", (resultSet, i) -> resultSet.getLong("id"), song.getTitle()).get(0);
            jdbcTemplate.update("delete from entry where song_id = ?", id);
            jdbcTemplate.update("delete from song where title = ?", song.getTitle());
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // TODO: jdbc updates....
    @Override
    public void updateSong(Song song, Song newSong) {
        try {
            jdbcTemplate.update("update song set title = ?, duration = ?, album_index = ? where id = ?", newSong.getTitle(), newSong.getLength(), newSong.getIndex(), song.getId());
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Song> findSongByAlbumName(String albumName) {
        return readSongs().stream().filter(song -> song.getAlbum().getName().equals(albumName)).toList();
    }
}