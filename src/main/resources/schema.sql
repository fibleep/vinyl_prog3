DROP TABLE IF EXISTS entry CASCADE;
DROP TABLE IF EXISTS author CASCADE;
DROP TABLE IF EXISTS song CASCADE;
DROP TABLE IF EXISTS album CASCADE;

CREATE TABLE author
(
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(50) NOT NULL,
    age    INTEGER     NOT NULL,
    gender VARCHAR(6)  NOT NULL CHECK (gender in ('FEMALE', 'MALE', 'OTHER'))
);
CREATE TABLE album
(
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    title        TEXT    NOT NULL,
    release_year INTEGER NOT NULL,
    genre        TEXT    NOT NULL CHECK (genre in ('ALT', 'RAP', 'POP'))
);
CREATE TABLE song
(
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    title       TEXT  NOT NULL,
    duration    FLOAT NOT NULL,
    album_index INTEGER
);
CREATE TABLE entry
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    author_id VARCHAR(50) NOT NULL REFERENCES author (id) ON DELETE CASCADE,
    song_id   INT         NOT NULL REFERENCES song (id) ON DELETE CASCADE,
    album_id  INT         NOT NULL REFERENCES album (id) ON DELETE CASCADE,
    co_author BOOL DEFAULT FALSE
);


