package com.musicdatabase.service.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity

public class Album {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 40)
    private String name;
    transient private List<Song> songs = new ArrayList<>();
    @Column(name = "year", nullable = false)
    private LocalDateTime year;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name = "author", nullable = false)
    transient Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album(String name, LocalDateTime year, Genre genre, Author author, List<Song> songs) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.author = author;
        this.songs = songs;
    }


    public int getYear() {
        return year.getYear();
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void updateSong(Song originalSong, Song newSong) {
        List<Song> updatedSongs = new ArrayList<>(this.songs);
        updatedSongs.remove(originalSong);
        updatedSongs.add(newSong);
        this.songs = updatedSongs;
    }

    @Override
    public String toString() {
        // get all song names
        StringBuilder songNames = new StringBuilder();
        for (Song song : this.songs) {
            songNames.append(song.getTitle()).append(" | ");
        }
        return "\nALBUM" +
                "\n------------------" +
                "\n [ NAME : " + name +
                " | SONGS : " + songNames +
                "YEAR : " + year +
                " | AUTHORS : " + author.getName() +
                " | GENRE : " + genre +
                "\n------------------";
    }
}
