package com.musicdatabase.service.repository;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JsonDataWriter {
    private static final String SONGS_JSON = "songs.json";
    private static final String AUTHORS_JSON = "authors.json";
    private static final String ALBUMS_JSON = "albums.json";
    private final Gson gson;

    public JsonDataWriter() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDateTime.class, new presentation.LocalDateTimeSerializer());
        this.gson = builder.create();
    }

    public void writeSongs(SongRepository songs) {
        try (FileWriter writer = new FileWriter(SONGS_JSON)) {
            String json = gson.toJson(songs);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save Songs to JSON", e);
        }
    }

    public void writeAuthors(AuthorRepository authors) {
        try (FileWriter writer = new FileWriter(AUTHORS_JSON)) {
            String json = gson.toJson(authors);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save authors to JSON", e);
        }
    }

    public void writeAlbums(AlbumRepository albums) {
        try (FileWriter writer = new FileWriter(ALBUMS_JSON)) {
            String json = gson.toJson(albums);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save albums to JSON", e);
        }
    }

}
