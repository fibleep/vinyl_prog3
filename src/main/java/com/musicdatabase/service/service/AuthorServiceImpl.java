package com.musicdatabase.service.service;

import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.AuthorRepository;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AlbumService albumService;
    private final SongService songService;
    private final JsonDataWriter jsonDataWriter;

    public AuthorServiceImpl(AuthorRepository authorRepository, JsonDataWriter jsonDataWriter, SongService songService, AlbumService albumService) {
        this.albumService = albumService;
        this.songService = songService;
        this.authorRepository = authorRepository;
        this.jsonDataWriter = jsonDataWriter;
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.readAuthors();
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.createAuthor(author);
    }

    @Override
    public void writeAuthorsToJSON(AuthorRepository authors) {
        jsonDataWriter.writeAuthors(authors);
    }

    @Override
    public void updateAuthor(Author originalAuthor, Author newAuthor) {
        authorRepository.updateAuthor(originalAuthor, newAuthor);
    }

    @Override
    public void removeAuthor(Author author) {
        for (Song song : songService.readSongsByAuthor(author.getName())) {
            Song updatedSong = song;
            updatedSong.removeAuthor(author);
            if (updatedSong.getAuthors().size() == 0) {
                songService.removeSong(song);
            } else {
                songService.updateSong(song, updatedSong);
            }
        }
        for (Album album : author.getAlbums()) {
            albumService.removeAlbum(album);
        }
        authorRepository.removeAuthor(author);
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorRepository.readAuthors().stream()
                .filter(author -> author.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
