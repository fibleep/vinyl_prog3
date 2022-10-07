package com.musicdatabase.service.presentation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.musicdatabase.service.domain.Album;
import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.domain.Song;
import com.musicdatabase.service.repository.DataFactoryImplementation;



import java.util.List;
import java.util.Scanner;

public class View {
    public void initialize() {
        DataFactoryImplementation.seed();
        List<Author> authors = DataFactoryImplementation.getAuthors();
        List<Album> albums = DataFactoryImplementation.getAlbums();
        List<Song> songs = DataFactoryImplementation.getSongs();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        JsonDataWriter jsonWriter = new JsonDataWriter();
        while(true) {
            switch (presentation.GUI.menu()) {
                case SHOW_SONGS -> {
                    for (Song song : songs) {
                        System.out.println(song);
                        jsonWriter.writeSongs(songs);
                    }
                }
                case SHOW_AUTHORS -> {
                    for (Author author : authors) {
                        System.out.println(author);
                        jsonWriter.writeAuthors(authors);
                    }
                }
                case SHOW_ALBUMS -> {
                    for (Album album : albums) {
                        System.out.println(album);
                        jsonWriter.writeAlbums(albums);
                    }
                }
                case SHOW_SONGS_OF_AUTHOR -> {
                    System.out.println("Enter author name: ");
                    Scanner scanner = new Scanner(System.in);
                    String authorName = scanner.nextLine();
                    songs.stream().filter(song -> song.getAuthor().stream().anyMatch(author -> author.getName().equals(authorName))).forEach(System.out::println);
                    jsonWriter.writeSongs(songs);
                }
                case SHOW_ALBUMS_OF_AUTHOR_FROM_YEAR -> {
                    System.out.println("Enter author name: ");
                    Scanner scanner = new Scanner(System.in);
                    String authorName = scanner.nextLine();
                    System.out.println("Enter year: ");
                    int year = scanner.nextInt();
                    albums.stream().filter(album -> album.getArtist().getName().equals(authorName) && album.getYear().getYear() == year).forEach(System.out::println);
                    jsonWriter.writeAlbums(albums);
                }
            }
        }
    }
}
