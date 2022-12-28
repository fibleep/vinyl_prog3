package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.viewmodel.SongViewModel;
import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Song;
import com.musicdatabase.service.model.session.PageVisit;
import com.musicdatabase.service.service.AlbumService;
import com.musicdatabase.service.service.AuthorService;
import com.musicdatabase.service.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public ModelAndView addSong(Model model, HttpServletRequest request) {
        logger.info("addSong called");
        model.addAttribute("authors", authorService.getAuthors().stream().map(Author::getName).toArray());
        model.addAttribute("albums", albumService.getAlbums().stream().map(Album::getName).toArray());
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/song/addsong", "songViewModel", new SongViewModel());
    }

    @PostMapping("/addsong")
    public ModelAndView addSong(@Valid @ModelAttribute("songViewModel") SongViewModel songViewModel, BindingResult bindingResult, HttpServletRequest request) {
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
        song.addAuthor(authorService.getAuthors().stream().filter(author -> author.getName().equals(songViewModel.getAuthor())).findFirst().get());
        if (songViewModel.getAlbum() != null) {
            song.setAlbum(albumService.getAlbums().stream().filter(album -> album.getName().equals(songViewModel.getAlbum())).findFirst().get());
        }
        songService.createSong(song);
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("redirect:/songs");
    }

    @GetMapping("/{song}")
    public ModelAndView getSong(@PathVariable String song, HttpServletRequest request) {
        logger.info("getSong called");
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/song/song-details", "song", songService.getSongs().stream().filter(s -> s.getTitle().equals(song)).findFirst().get());
    }

    @GetMapping
    public ModelAndView getSongs(Model model, HttpServletRequest request) {
        logger.info("getSongs called");
        model.addAttribute("authors", authorService.getAuthors().stream().map(Author::getName).toArray());
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/song/songs", "songs", songService.getSongs());
    }

}
