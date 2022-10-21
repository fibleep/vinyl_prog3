package com.musicdatabase.service.controller;

import com.musicdatabase.service.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping
    public ModelAndView getSongs() {
        logger.info("getSongs called");
        return new ModelAndView("songs", "songs", songService.getSongs());
    }

}
