import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class JsonWriter {
    private static final String SONGS_JSON = "songs.json";
    private static final String AUTHORS_JSON = "authors.json";
    private static final String ALBUMS_JSON = "albums.json";
    private Gson gson;

    public JsonWriter() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gson = builder.create();
    }

    public void writeSongs(List<Song> songs) {
        String json = gson.toJson(songs);
        try (FileWriter writer = new FileWriter(SONGS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save Songs to JSON", e);
        }
    }

    public void writeAuthors(List<Author> authors) {
        String json = gson.toJson(authors);
        try (FileWriter writer = new FileWriter(AUTHORS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save authors to JSON", e);
        }
    }

    public void writeAlbums(List<Album> albums) {
        String json = gson.toJson(albums);
        try (FileWriter writer = new FileWriter(ALBUMS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save albums to JSON", e);
        }
    }

}
