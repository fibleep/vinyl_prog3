package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.AuthorViewModel;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.repository.AuthorRepository;
import com.musicdatabase.service.repository.AuthorRepositorySpring;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Profile("spring")
public class AuthorServiceSpring implements AuthorService {
    private final AuthorRepositorySpring authorRepository;
    private final AlbumService albumService;
    private final SongService songService;
    private final JsonDataWriter jsonDataWriter;
    private final Logger logger = Logger.getLogger(AuthorServiceSpring.class.getName());

    public AuthorServiceSpring(AuthorRepositorySpring authorRepository, JsonDataWriter jsonDataWriter, SongService songService, AlbumService albumService) {
        this.albumService = albumService;
        this.songService = songService;
        this.authorRepository = authorRepository;
        this.jsonDataWriter = jsonDataWriter;
    }

    @Override
    public List<Author> getAuthors() {
        logger.info("getAuthors called");
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByName(String name) {
        logger.info("getAuthorByName called with name: " + name);
        return authorRepository.findByName(name);
    }

    @Override
    public void writeAuthorsToJSON(AuthorRepository authors) {

    }

    @Override
    public void addAuthor(Author author) {
        logger.info("addAuthor called with author: " + author);
        authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Author originalAuthor, Author newAuthor) {
        logger.info("updateAuthor called with originalAuthor: " + originalAuthor + " and newAuthor: " + newAuthor);
        authorRepository.save(newAuthor);
    }

    @Override
    public void removeAuthor(Author author) {
        logger.info("removeAuthor called with author: " + author);
        author.getAlbums().forEach(albumService::removeAlbum);
        author.getSongs().forEach(song -> {
            song.removeAuthor(author);
            songService.updateSong(song, song);
        });
        authorRepository.delete(author);
    }

    @Override
    public void mergeAuthorWithModel(Author originalAuthor, AuthorViewModel authorViewModel) {

    }
}
