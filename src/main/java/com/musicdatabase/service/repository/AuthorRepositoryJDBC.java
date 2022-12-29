package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Gender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("JDBC")
public class AuthorRepositoryJDBC implements AuthorRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    private JdbcTemplate jdbcTemplate;

    public AuthorRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> readAuthors() {
        return jdbcTemplate.query("SELECT * FROM author", (resultSet, i) -> {
            Author author = new Author();
            author.setName(resultSet.getString("name"));
            author.setAge(resultSet.getInt("age"));
            author.setGender(Gender.valueOf(resultSet.getString("gender")));
            return author;
        });
    }

    @Override
    public Author createAuthor(Author author) {
        return null;
    }

    @Override
    public void removeAuthor(Author author) {

    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {

    }
}
