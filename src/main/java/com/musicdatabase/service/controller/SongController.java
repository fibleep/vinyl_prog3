package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.exceptions.EntryNotFoundException;
import com.musicdatabase.service.controller.viewmodel.SongViewModel;
import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.model.session.PageVisit;
import com.musicdatabase.service.service.AlbumService;
import com.musicdatabase.service.service.AuthorService;
import com.musicdatabase.service.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final Logger logger = Logger.getLogger(SongController.class.getName());
    @Autowired
    private SongService songService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private HistoryController historyController;

    public SongController(SongService songService, AuthorService authorService, AlbumService albumService) {
        logger.info("SongController created");
        this.songService = songService;
        this.authorService = authorService;
        this.albumService = albumService;
    }

    @GetMapping("/addsong")
    public ModelAndView addSong(HttpServletRequest request) {
        logger.info("addSong called");
        ModelAndView modelAndView = new ModelAndView("/song/addsong");
        modelAndView.addObject("authors", authorService.getAuthors().stream().map(Author::getName).toArray());
        modelAndView.addObject("albums", albumService.getAlbums().stream().map(Album::getName).toArray());
        modelAndView.addObject("songViewModel", new SongViewModel());
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return modelAndView;
    }

    @PostMapping("/addsong")
    public ModelAndView addSong(@Valid @ModelAttribute("songViewModel") SongViewModel songViewModel,
                                BindingResult bindingResult, HttpServletRequest request) {
        logger.info("addSong called");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.warning(error.toString()));
            return new ModelAndView("/song/addsong");
        }
        Song song = new Song();
        song.setTitle(songViewModel.getTitle());
        song.setIndex(songViewModel.getIndex());
        double length = songViewModel.getMinutes() + (songViewModel.getSeconds() / 100.0);
        song.setLength(length);
        song.addAuthor(authorService.getAuthors().stream()
                .filter(author -> author.getName().equals(songViewModel.getAuthor())).findFirst().get());
        if (songViewModel.getAlbum() != null) {
            Album album = albumService.getAlbums().stream().filter(a -> a.getName().equals(songViewModel.getAlbum())).findFirst().get();
            song.setAlbum(album);
            if (songViewModel.getIndex() != 0 && songViewModel.getIndex() != song.getIndex() && !album.getSongIndexes().contains(songViewModel.getIndex())) {
                song.setIndex(songViewModel.getIndex());
            }

        }
        songService.createSong(song);
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("redirect:/songs");
    }

    @GetMapping("/{song}")
    public ModelAndView getSong(@PathVariable String song, HttpServletRequest request) throws EntryNotFoundException {
        logger.info("getSong called");
        ModelAndView modelAndView = new ModelAndView("/song/song-details");
        try {
            Song songObject = songService.getSongs().stream()
                    .filter(song1 -> song1.getTitle().equals(song)).findFirst().get();
            modelAndView.addObject("authors", authorService.getAuthors().stream().map(Author::getName).toArray());
            modelAndView.addObject("song", songObject);
            modelAndView.addObject("albums", albumService.getAlbums().stream().map(Album::getName).toArray());
            modelAndView.addObject("songViewModel", new SongViewModel());
            historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
            return modelAndView;
        } catch (Exception e) {
            throw new EntryNotFoundException("Song not found");
        }
    }

    @DeleteMapping("/{song}")
    public ModelAndView deleteSong(@PathVariable String song, HttpServletRequest request) {
        logger.info("deleteSong called");
        songService.removeSong(songService.getSong(song));
        return new ModelAndView("redirect:/songs");
    }

    @PutMapping("/{song}")
    public ModelAndView updateSong(@PathVariable String song, @Valid @ModelAttribute("songViewModel") SongViewModel songViewModel, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("updateSong called");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.warning(error.toString()));
            return new ModelAndView("redirect:/song/song-details");
        }
        Song originalSong = songService.getSong(song);
        Song songToUpdate = originalSong;
        // set title
        if (!songToUpdate.getTitle().equals(songViewModel.getTitle()) && !songViewModel.getTitle().isEmpty()) {
            songToUpdate.setTitle(songViewModel.getTitle());
        }
        // add authors
        if (songViewModel.getAuthor() != null) {
            String[] authorsArray = songViewModel.getAuthor().split(",");
            List<Author> authors = new ArrayList<>();
            for (String author : authorsArray) {
                authors.add(authorService.getAuthors().stream().filter(a -> a.getName().equals(author)).findFirst().get());
            }
            songToUpdate.setAuthors(authors);
        }
        // set index
        if (songViewModel.getMinutes() != 0 && songViewModel.getSeconds() != 0) {
            double length = songViewModel.getMinutes() + (songViewModel.getSeconds() / 100.0);
            songToUpdate.setLength(length);
        }
        if (songViewModel.getAlbum() != null) {
            Album album = albumService.getAlbums().stream().filter(a -> a.getName().equals(songViewModel.getAlbum())).findFirst().get();
            songToUpdate.setAlbum(album);
            if (songViewModel.getIndex() != 0 && songViewModel.getIndex() != originalSong.getIndex() && !album.getSongIndexes().contains(songViewModel.getIndex())) {
                songToUpdate.setIndex(songViewModel.getIndex());
            }
            Album originaAlbum = originalSong.getAlbum();
            Album updatedAlbum = originaAlbum;
            updatedAlbum.updateSong(originalSong, songToUpdate);
            albumService.updateAlbum(originaAlbum, updatedAlbum);
        }
        songService.updateSong(originalSong, songToUpdate);
        return new ModelAndView("redirect:/songs");
    }

    @GetMapping
    public ModelAndView getSongs(HttpServletRequest request) {
        logger.info("getSongs called");
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/song/songs", "songs", songService.getSongs());
    }

}
