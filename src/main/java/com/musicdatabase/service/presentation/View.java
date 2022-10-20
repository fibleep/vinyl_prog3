package com.musicdatabase.service.presentation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.musicdatabase.service.domain.Album;
import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.domain.Song;
import com.musicdatabase.service.repository.*;
import com.musicdatabase.service.service.AlbumService;
import com.musicdatabase.service.service.AuthorService;
import com.musicdatabase.service.service.SongService;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
public class View {
    private final AlbumService albumService;
    private final AuthorService authorService;
    private final SongService songService;

    public View(AlbumService albumService, AuthorService authorService, SongService songService) {
        this.albumService = albumService;
        this.authorService = authorService;
        this.songService = songService;
    }
    public void initialize() {
        AlbumRepository albumRepository = new ListAlbumRepository();
        AuthorRepository authorRepository = new ListAuthorRepository();
        SongRepository songRepository = new ListSongRepository();
        DataFactory dataFactory = new DataSeeder(authorRepository, songRepository,albumRepository);
        DataSeeder.seed();



        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        while(true) {
            switch (presentation.GUI.menu()) {
                case SHOW_SONGS -> {
                    songService.readSongs().forEach(System.out::println);
                }
                case SHOW_AUTHORS -> {
                    authorService.readAuthors().forEach(System.out::println);
                }
                case SHOW_ALBUMS -> {
                    albumService.readAlbums().forEach(System.out::println);
                }
                case SHOW_SONGS_OF_AUTHOR -> {
                    System.out.println("Enter author name: ");
                    Scanner scanner = new Scanner(System.in);
                    String authorName = scanner.nextLine();
                    songService.readSongsByAuthor(authorName).forEach(System.out::println);
                }
                case SHOW_ALBUMS_OF_AUTHOR_FROM_YEAR -> {
                    System.out.println("Enter author name: ");
                    Scanner scanner = new Scanner(System.in);
                    String authorName = scanner.nextLine();
                    System.out.println("Enter year: ");
                    int year = scanner.nextInt();
                    albumService.readAlbumsByAuthorAndYear(authorName,year).forEach(System.out::println);
                }
            }
        }
    }
}
