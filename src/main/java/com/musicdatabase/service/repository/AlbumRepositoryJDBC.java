package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Profile("JDBC")
@Repository
public class AlbumRepositoryJDBC implements AlbumRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SongRepository songRepository;

    @Override
    public Album createAlbum(Album album) {
        jdbcTemplate.update("INSERT INTO album (title,release_year) values (?,?)", album.getName(), album.getYear());
        return album;
    }

    @Override
    public List<Album> readAlbums() {
        // TODO: THIS DOESNT WORK, AUTHOR ISNT DISPLAYED PROPERLY, SONGS

        return jdbcTemplate.query("SELECT * FROM album", (resultSet, i) -> {
            Album album = new Album();
            album.setName(resultSet.getString("title"));
            album.setYear(LocalDateTime.of(resultSet.getInt("release_year"), 1, 1, 0, 0));
            album.setSongs(songRepository.findSongByAlbumName(album.getName()));
            album.setGenre(Genre.valueOf(resultSet.getString("genre")));
            return album;
        });
    }


    @Override
    public void deleteAlbum(Album album) {
        jdbcTemplate.update("DELETE FROM album WHERE title = ?", album.getName());
    }

    @Override
    public void updateAlbum(Album album, Album newAlbum) {
        jdbcTemplate.update("UPDATE album SET title = ?, release_year = ? WHERE title = ?", newAlbum.getName(), newAlbum.getYear(), album.getName());
    }

    @Override
    public Album findAlbumBySongTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM album WHERE id = (select album_id from entry where song_id = (select id from song where title=?))", (resultSet, i) -> {
            Album album = new Album();
            album.setName(resultSet.getString("title"));
            album.setYear(LocalDateTime.of(resultSet.getInt("release_year"), 1, 1, 0, 0));
            album.setSongs(songRepository.findSongByAlbumName(album.getName()));
            album.setGenre(Genre.valueOf(resultSet.getString("genre")));
            return album;
        }, title);
    }


}
