package presentation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Album;
import domain.Author;
import domain.Song;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class JsonDataWriter {
    private static final String SONGS_JSON = "songs.json";
    private static final String AUTHORS_JSON = "authors.json";
    private static final String ALBUMS_JSON = "albums.json";

    public JsonDataWriter() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        // builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        Gson gson = builder.create();
    }

    public void writeSongs(List<Song> songs) {
        try (FileWriter writer = new FileWriter(SONGS_JSON)) {
            String json ="a"; // gson.toJson(songs);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save Songs to JSON", e);
        }
    }

    public void writeAuthors(List<Author> authors) {
        try (FileWriter writer = new FileWriter(AUTHORS_JSON)) {
            String json ="a"; // gson.toJson(authors);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save authors to JSON", e);
        }
    }

    public void writeAlbums(List<Album> albums) {
        try (FileWriter writer = new FileWriter(ALBUMS_JSON)) {
            String json ="a"; // gson.toJson(albums);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save albums to JSON", e);
        }
    }

}
