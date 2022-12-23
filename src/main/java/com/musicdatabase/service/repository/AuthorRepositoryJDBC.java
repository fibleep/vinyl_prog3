package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
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

    @Override
    public List<Author> readAuthors() {
        return null;
    }

    @Override
    public Author createAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthor(Author author) {

    }

    @Override
    public void updateAuthor(Author author, Author newAuthor) {

    }
}
