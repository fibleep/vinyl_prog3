package com.musicdatabase.service.model;

import java.time.LocalDateTime;
import java.util.List;

public class Album {
    String name;
    List<Song> songs;
    LocalDateTime year;
    Genre genre;
    transient Author author;

    public Album(String name, LocalDateTime year, Genre genre, Author author, List<Song> songs) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.author = author;
        this.songs = songs;
    }

    public Album() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year.getYear();
    }

    public String getYearString() {
        return year.getYear() + "";
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
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
                " | AUTHORS : " + author.getName() +
                " | GENRE : " + genre +
                "\n------------------";
    }
}
