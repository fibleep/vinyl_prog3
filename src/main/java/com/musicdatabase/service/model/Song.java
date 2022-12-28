package com.musicdatabase.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Song {
    private String title;
    private int index;
    private double length;
    private transient List<Author> authors = new ArrayList<>();
    private transient Album album = null;

    public Song(String title, int index, double length, List<Author> authors, Album album) {
        this.title = title;
        this.index = index;
        this.length = length;
        this.authors = authors;
        this.album = album;
    }

    public Song() {
    }

    public int getMinutes() {
        return (int) this.length;
    }

    public int getSeconds() {
        return (int) ((this.length - this.getMinutes()) * 100);
    }


    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void removeAuthor(Author author) {
        List<Author> updatedAuthors = new ArrayList<>(authors);
        updatedAuthors.remove(author);
        authors = updatedAuthors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        // get a list of all the author names
        StringBuilder authorNames = new StringBuilder();
        for (Author author : this.authors) {
            authorNames.append(author.getName()).append(" | ");
        }
        return "\nSONG" +
                "\n------------------" +
                "\n [ TITLE : " + title +
                " | INDEX : " + index +
                " | LENGTH : " + length +
                " | AUTHOR : " + authorNames +
                "ALBUM : " + album.getName() + " ]" +
                "\n------------------";
    }
}
