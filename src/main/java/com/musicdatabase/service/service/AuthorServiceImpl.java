package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.converter.StringToGenderConverter;
import com.musicdatabase.service.controller.viewmodel.AuthorViewModel;
import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.AuthorRepository;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Profile({"JPA", "JDBC", "collections"})
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AlbumService albumService;
    private final SongService songService;
    private final JsonDataWriter jsonDataWriter;
    private final Logger logger = Logger.getLogger(AuthorServiceImpl.class.getName());

    public AuthorServiceImpl(AuthorRepository authorRepository, JsonDataWriter jsonDataWriter, SongService songService, AlbumService albumService) {
        this.albumService = albumService;
        this.songService = songService;
        this.authorRepository = authorRepository;
        this.jsonDataWriter = jsonDataWriter;
    }

    @Override
    public List<Author> getAuthors() {
        logger.info("getAuthors called");
        return authorRepository.readAuthors();
    }

    @Override
    public void addAuthor(Author author) {
        logger.info("addAuthor called with author: " + author);
        authorRepository.createAuthor(author);
    }

    @Override
    public void writeAuthorsToJSON(AuthorRepository authors) {
        jsonDataWriter.writeAuthors(authors);
    }

    @Override
    public void updateAuthor(Author originalAuthor, Author newAuthor) {
        logger.info("updateAuthor called with originalAuthor: " + originalAuthor + " and newAuthor: " + newAuthor);
        authorRepository.updateAuthor(originalAuthor, newAuthor);
    }
    @Override
    public void removeAuthor(Author author) {
        logger.info("removeAuthor called with author: " + author);
        for (Song song : songService.findSongsByAuthorName(author.getName())) {
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
        logger.info("getAuthorByName called with name: " + name);
        return authorRepository.readAuthors().stream()
                .filter(author -> author.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
