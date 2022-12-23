package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Genre;
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
    private JdbcTemplate jdbcTemplate;

    @Override
    public Album createAlbum(Album album) {
        jdbcTemplate.update("INSERT INTO album (title,realese_year) values (?,?)", album.getName(), album.getYear());
        return album;
    }

    @Override
    public List<Album> readAlbums() {
        jdbcTemplate.query("SELECT * FROM album", (resultSet, i) -> {
            Album album = new Album();
            album.setAuthor(null);
            album.setGenre(Genre.valueOf(resultSet.getString("genre")));
            album.setYear(LocalDateTime.of(resultSet.getInt("year"), 1, 1, 0, 0));
            return album;
        });
        return null;
    }

    @Override
    public void deleteAlbum(Album album) {
        jdbcTemplate.update("DELETE FROM album WHERE title = ?", album.getName());
    }

    @Override
    public void updateAlbum(Album album, Album newAlbum) {

    }
}
