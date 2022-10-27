package com.musicdatabase.service.controller;

import com.musicdatabase.service.domain.Album;
import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.domain.Genre;
import com.musicdatabase.service.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Logger;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private Logger logger = Logger.getLogger(AlbumController.class.getName());

    @Autowired
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        logger.info("AlbumController created");
        this.albumService = albumService;
    }

    @GetMapping("/addalbum")
    public ModelAndView showAddAlbum() {
        logger.info("addAlbum called");
        return new ModelAndView("addalbum","genres", Genre.values());
    }
    @PostMapping("/addalbum")
    public ModelAndView processAddAlbum(String name, int year, String genre) {
        logger.info("addAlbum called");
        LocalDateTime localDateTime = LocalDateTime.of(year,1,1,0,0);
        Genre genreValue = Genre.valueOf(genre);
        albumService.addAlbum(new Album(name,localDateTime,genreValue,null,null));
        return new ModelAndView("redirect:/albums");
    }

    @GetMapping("/{album}")
    public ModelAndView showAlbum(@PathVariable String album) {
        logger.info("showAlbum called");
        return new ModelAndView("album-details","album",albumService.getAlbums().stream().filter(album1 -> album1.getName().equals(album)).findFirst().get());
    }

    @GetMapping
    public ModelAndView getAlbums() {
        logger.info("getAlbums called");
        return new ModelAndView("albums", "albums", albumService.getAlbums());
    }
}
