package com.musicdatabase.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Author {
    String name;
    int age;
    Gender gender;
    transient List<Album> albums;
    transient List<Song> songs;

    public Author(String name, int age, Gender gender, List<Album> albums, List<Song> songs) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.albums = albums;
        this.songs = songs;
    }

    public Author() {
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
