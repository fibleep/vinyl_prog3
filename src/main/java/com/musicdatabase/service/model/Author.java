package com.musicdatabase.service.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Author {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 40)
    private String name;
    @Column(name = "age")
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albums = null;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "author_song",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs = null;


    public Author(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Author(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

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

    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    public void removeAlbum(Album album) {
        this.albums.remove(album);
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void updateAlbum(Album originalAlbum, Album newAlbum) {
        List<Album> updatedAlbums = this.albums;
        updatedAlbums.remove(originalAlbum);
        updatedAlbums.add(newAlbum);
        this.albums = updatedAlbums;
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
