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
    transient List<Album> album;
    transient List<Song> song;

    public Author(String name, int age, Gender gender, List<Album> album, List<Song> song) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.album = album;
        this.song = song;
    }
    public Author() {
    }

    public void addSong(Song song) {
        this.song.add(song);
    }

    public void removeSong(Song song) {
        this.song.remove(song);
    }

    public void setSong(List<Song> song) {
        this.song = song;
    }

    @Override
    public String toString() {
        // get a list of all the album names
        StringBuilder albumNames = new StringBuilder();
        for (Album album : this.album) {
            albumNames.append(album.getName()).append(" | ");
        }
        // get a list of all the song names
        StringBuilder songNames = new StringBuilder();
        for (Song song : this.song) {
            songNames.append(song.getTitle()).append(" | ");
        }

        return "\nAUTHOR" +
                "\n------------------" +
                "\n [ NAME : " + name +
                " | AGE : " + age +
                " | GENDER : " + gender +
                " | ALBUMS : " + albumNames+
                "SONGS : " + songNames+"" +
                "\n------------------";
    }
}
