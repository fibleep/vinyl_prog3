package com.musicdatabase.service.controller;

import com.musicdatabase.service.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping
    public ModelAndView getAlbums() {
        logger.info("getAlbums called");
        return new ModelAndView("albums", "albums", albumService.getAlbums());
    }
}
