package com.musicdatabase.service.controller;

import com.musicdatabase.service.domain.Song;
import com.musicdatabase.service.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final Logger logger = Logger.getLogger(SongController.class.getName());
    @Autowired
    private SongService songService;
    public SongController(SongService songService) {
        logger.info("MusicController created");
        this.songService = songService;
    }
    @GetMapping("/addsong")
    public ModelAndView addSong() {
        logger.info("addSong called");
        return new ModelAndView("addsong");
    }
    @PostMapping("/addsong")
    public ModelAndView addSong(String name, int duration, int index) {
        logger.info("addSong called");
        Song song = null;
        songService.addSong(new Song(name, index, duration, null, null));
        return new ModelAndView("redirect:/songs");
    }
    @GetMapping("/{song}")
    public ModelAndView getSong(@PathVariable String song) {
        logger.info("getSong called");
        return new ModelAndView("song-details", "song", songService.getSongs().stream().filter(s -> s.getTitle().equals(song)).findFirst().get());
    }
    @GetMapping
    public ModelAndView getSongs() {
        logger.info("getSongs called");
        return new ModelAndView("songs", "songs", songService.getSongs());
    }

}
