package com.musicdatabase.service.domain;

import java.util.List;

public class Song {
    private String title;
    private int index;
    private double length;
    private transient List<Author> authors;
    private transient Album album;

    public Song(String title, int index, double length, List<Author> authors, Album album) {
        this.title = title;
        this.index = index;
        this.length = length;
        this.authors = authors;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void removeAuthor(Author author) {
        this.authors.remove(author);
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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
                " | AUTHOR : " + authorNames+
                "ALBUM : " + album.getName()+" ]" +
                "\n------------------";
    }
}
