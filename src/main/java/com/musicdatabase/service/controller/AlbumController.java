package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.viewmodel.AlbumViewModel;
import com.musicdatabase.service.domain.Album;
import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.domain.Genre;
import com.musicdatabase.service.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());

    @Autowired
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        logger.info("AlbumController created");
        this.albumService = albumService;
    }

    @GetMapping("/addalbum")
    public ModelAndView showAddAlbum(Model model) {
        logger.info("addAlbum called");
        model.addAttribute("albumViewModel", new AlbumViewModel());
        return new ModelAndView("/album/addalbum","genres", Genre.values());
    }
    @PostMapping("/addalbum")
    public ModelAndView processAddAlbum(@Valid @ModelAttribute("albumViewModel") AlbumViewModel albumViewModel,BindingResult bindingResult) {
        logger.info("addAlbum called");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.warning(error.toString()));
            return new ModelAndView("/album/addalbum");
        }
        LocalDateTime localDateTime = LocalDateTime.of(parseInt(albumViewModel.getYear()),1,1,0,0);
        Genre genreValue = Genre.valueOf(albumViewModel.getGenre());
        Album album = new Album();
        album.setName(albumViewModel.getName());
        album.setYear(localDateTime);
        album.setGenre(genreValue);
        albumService.addAlbum(album);
        return new ModelAndView("redirect:/albums");
    }

    @GetMapping("/{album}")
    public ModelAndView showAlbum(@PathVariable String album) {
        logger.info("showAlbum called");
        return new ModelAndView("/album/album-details","album",albumService.getAlbums().stream().filter(album1 -> album1.getName().equals(album)).findFirst().get());
    }

    @GetMapping
    public ModelAndView getAlbums() {
        logger.info("getAlbums called");
        return new ModelAndView("/album/albums", "albums", albumService.getAlbums());
    }
}
