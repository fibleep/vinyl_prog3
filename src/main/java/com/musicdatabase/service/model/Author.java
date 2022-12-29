package com.musicdatabase.service.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 40)
    private String name;
    @Column(name = "age", nullable = false)
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    transient private List<Album> albums;
    transient private List<Song> songs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        List<Song> updatedSongs = this.songs;
        updatedSongs.remove(originalSong);
        updatedSongs.add(newSong);
        this.songs = updatedSongs;
    }

    @Override
    public String toString() {
        // get a list of all the album names
        StringBuilder albumNames = new StringBuilder();
        for (Album album : this.albums) {
            albumNames.append(album.getName()).append(" | ");
        }
        // get a list of all the song names
        StringBuilder songNames = new StringBuilder();
        for (Song song : this.songs) {
            songNames.append(song.getTitle()).append(" | ");
        }

        return "\nAUTHOR" +
                "\n------------------" +
                "\n [ NAME : " + name +
                " | AGE : " + age +
                " | GENDER : " + gender +
                " | ALBUMS : " + albumNames +
                "SONGS : " + songNames + "" +
                "\n------------------";
    }
}
