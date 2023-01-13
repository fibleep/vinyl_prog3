package com.musicdatabase.service.repository;

import com.musicdatabase.service.controller.exceptions.DatabaseException;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Repository
@Profile("JDBC")
public class AuthorRepositoryJDBC implements AuthorRepository {
    private final SimpleJdbcInsert jdbcInsert;
    private final Logger logger = Logger.getLogger(AuthorRepositoryJDBC.class.getName());
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AuthorRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("author")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Author> readAuthors() {
        try {
            return jdbcTemplate.query("SELECT * FROM author", (resultSet, i) -> {
                Author author = new Author();
                author.setName(resultSet.getString("name"));
                author.setAge(resultSet.getInt("age"));
                author.setGender(Gender.valueOf(resultSet.getString("gender")));
                return author;
            });
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Author createAuthor(Author author) {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", author.getName());
            parameters.put("age", author.getAge());
            parameters.put("gender", author.getGender().toString());
            Number id = jdbcInsert.executeAndReturnKey(parameters);
            author.setId((long) id.intValue());
            return author;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void removeAuthor(Author author) {
        try {
            long id = jdbcTemplate.query("select id from AUTHOR where name = ?", (resultSet, i) -> resultSet.getLong("id"), author.getName()).get(0);
            jdbcTemplate.update("delete from entry where author_id = ?", id);
            jdbcTemplate.update("delete from author where name = ?", author.getName());
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {
        try {
            logger.info("this should be implemented someday");
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    @Override
    public List<Author> findAuthorBySongTitle(String title) {
        logger.info("findAuthorBySongTitle called");
        try {
            return jdbcTemplate.query("SELECT * from author where id in (select author_id from entry where song_id=(select id from song where title=?))", this::mapRow, title);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Author findAuthorByAlbumName(String album) {
        return jdbcTemplate.query("SELECT * from author where id in (select author_id from entry where album_id=(select id from album where title=?))", this::mapRow, album).get(0);
    }

    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(rs.getString("name"), rs.getInt("age"), Gender.valueOf(rs.getString("gender")));
    }
}
