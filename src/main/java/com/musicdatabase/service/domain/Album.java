package com.musicdatabase.service.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Album {
    String name;
    List<Song> songs;
    LocalDateTime year;
    Genre genre;
    transient Author artist;

    public Album(String name, LocalDateTime year, Genre genre, Author artist, List<Song> songs) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.artist = artist;
        this.songs = songs;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getYear() {
        return year;
    }

    public void setYear(LocalDateTime year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getArtist() {
        return artist;
    }

    public void setArtist(Author artist) {
        this.artist = artist;
    }

    public List<Song> getSongs() {
        return songs;
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
                " | AUTHORS : " + artist.getName() +
                " | GENRE : " + genre +
                "\n------------------";
    }
}
