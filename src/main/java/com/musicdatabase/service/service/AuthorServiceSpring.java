package com.musicdatabase.service.service;

import com.musicdatabase.service.controller.viewmodel.AuthorViewModel;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Gender;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.repository.AuthorRepositorySpring;
import com.musicdatabase.service.repository.JsonDataWriter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Author merge(Author originalAuthor, AuthorViewModel authorViewModel) {
        originalAuthor.setName(authorViewModel.getName());
        originalAuthor.setAge(authorViewModel.getAge());
        originalAuthor.setGender(Gender.valueOf(authorViewModel.getGender()));
        return originalAuthor;
    }

    @Override
    @Transactional
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
        author.getAlbums().forEach(albumService::removeAlbum);
        authorRepository.delete(author);
    }

}
