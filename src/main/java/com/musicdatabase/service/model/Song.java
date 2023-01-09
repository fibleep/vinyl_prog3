package com.musicdatabase.service.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "song")
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false, unique = true, length = 40)
    private String title;
    @Column(name = "index", nullable = false)
    private int index;
    @Column(name = "length", nullable = false)
    private double length;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "song_author",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Song(String title, int index, double length, List<Author> authors, Album album) {
        this.title = title;
        this.index = index;
        this.length = length;
        this.authors = authors;
        this.album = album;
    }

    public Song(String title, int index, double length) {
        this.title = title;
        this.index = index;
        this.length = length;
        this.authors = new ArrayList<>();
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

    public List<Author> getAuthors() {
        return authors;
    }

    public boolean hasAuthor(String author) {
        return this.authors.stream().anyMatch(a -> a.getName().equals(author));
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
