package com.musicdatabase.service.configuration.h2;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("JDBC")
public class H2DatabaseCreator {
    private final JdbcTemplate jdbcTemplate;

    public H2DatabaseCreator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO: Figure out why this doesnt work
    @PostConstruct
    public void loadData() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS entry CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS album CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS song CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS author");
        jdbcTemplate.execute("CREATE TABLE album(id INTEGER PRIMARY KEY AUTO_INCREMENT, title TEXT    NOT NULL, release_year INTEGER NOT NULL, genre        TEXT    NOT NULL CHECK (genre in ('ALT', 'RAP', 'POP')))");
        jdbcTemplate.execute("CREATE TABLE song(" +
                " id          INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "    title       TEXT  NOT NULL," +
                "    duration    FLOAT NOT NULL," +
                "    album_index INTEGER)");
        jdbcTemplate.execute("CREATE TABLE author(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, age INTEGER NOT NULL,  gender VARCHAR(6)  NOT NULL CHECK (gender in ('FEMALE', 'MALE', 'OTHER')))");
        jdbcTemplate.execute("CREATE TABLE entry(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "    author_id VARCHAR(50) NOT NULL REFERENCES author (id) ON DELETE CASCADE," +
                "    song_id   INT         NOT NULL REFERENCES song (id) ON DELETE SET NULL," +
                "    album_id  INT          NOT NULL REFERENCES album (id) ON DELETE CASCADE," +
                "    co_author BOOL DEFAULT FALSE)");

        jdbcTemplate.execute("INSERT INTO AUTHOR(name, age, gender) VALUES ('Taco Hemingway', 32, 'MALE'), ('Dawid Podsiadło', 29, 'MALE'), ('Daria Zawiałow', 30, 'FEMALE'), ('Quebonafide', 31, 'MALE')");
        jdbcTemplate.execute("INSERT INTO ALBUM(title, release_year,genre)" +
                "VALUES ('Wojny i Noce', 2021,'ALT')," +
                "       ('POCZTÓWKA Z WWA, LATO ''19', 2019,'RAP')," +
                "       ('Wosk', 2016,'RAP')," +
                "       ('Małomiasteczkowy', 2018,'POP')," +
                "       ('ROMANTICPSYCHO', 2020,'RAP');");
        jdbcTemplate.execute("INSERT INTO SONG(title, duration, album_index)" +
                "VALUES" +
                "    ('Za krótki sen', 4.01, 4)," +
                "    ('Flower Night', 4.27, 12)," +
                "    ('Serce', 3.49, 11)," +
                "    ('Hollow', 3.54, 10)," +
                "    ('W PIĄTKI LEŻĘ W WANNIE', 3.48, 4)," +
                "    ('LECI NOWY FUTURE', 3.39, 3)," +
                "    ('SANATORIUM', 4.34, 9)," +
                "    ('WWA VHS', 4.41, 5)," +
                "    ('Wosk', 3.53, 1)," +
                "    ('BXL', 3.52, 2)," +
                "    ('Szczerze', 3.21, 3)," +
                "    ('Wiatr', 3.42, 4)," +
                "    ('Małomiasteczkowy', 3.23, 3)," +
                "    ('Najnowszy klip', 3.49, 4)," +
                "    ('Trofea', 3.44, 5)," +
                "    ('Nie ma fal', 3.58, 6)," +
                "    ('ROMANTICPSYCHO', 3.36, 1)," +
                "    ('JESIEŃ', 3.32, 2)," +
                "    ('TOKYO2020', 3.32, 12)," +
                "    ('BUBBLETEA', 4.44, 15);");
        jdbcTemplate.execute("INSERT INTO ENTRY(author_id, song_id, album_id, co_author)" +
                "VALUES" +
                "    (3, 1, 1, FALSE)," +
                "    (3, 2, 1, FALSE)," +
                "    (3, 3, 1, FALSE)," +
                "    (3, 4, 1, FALSE)," +
                "    (2, 20, 5, TRUE)," +
                "    (1, 5, 3, FALSE)," +
                "    (1, 6, 3, FALSE)," +
                "    (1, 7, 3, FALSE)," +
                "    (1, 8, 3, FALSE)," +
                "    (1, 9, 2, FALSE)," +
                "    (1, 10, 2, FALSE)," +
                "    (1, 11, 2, FALSE)," +
                "    (1, 12, 2, FALSE)," +
                "    (2, 13, 4, FALSE)," +
                "    (2, 14, 4, FALSE)," +
                "    (2, 15, 4, FALSE)," +
                "    (2, 16, 4, FALSE)," +
                "    (2, 1, 1, TRUE)," +
                "    (2, 9, 2, TRUE)," +
                "    (4, 17, 5, FALSE)," +
                "    (4, 18, 5, FALSE)," +
                "    (4, 19, 5, FALSE)," +
                "    (4, 20, 5, FALSE);");
    }
}
