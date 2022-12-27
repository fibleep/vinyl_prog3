package com.musicdatabase.service.controller;

import com.musicdatabase.service.controller.viewmodel.AlbumViewModel;
import com.musicdatabase.service.model.Album;
import com.musicdatabase.service.model.Author;
import com.musicdatabase.service.model.Genre;
import com.musicdatabase.service.model.session.PageVisit;
import com.musicdatabase.service.service.AlbumService;
import com.musicdatabase.service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final Logger logger = Logger.getLogger(AlbumController.class.getName());

    @Autowired
    private final AlbumService albumService;
    @Autowired
    private final AuthorService authorService;
    @Autowired
    private final HistoryController historyController;

    public AlbumController(AlbumService albumService, AuthorService authorService, HistoryController historyController) {
        logger.info("AlbumController created");
        this.albumService = albumService;
        this.authorService = authorService;
        this.historyController = historyController;
    }

    @GetMapping("/addalbum")
    public ModelAndView showAddAlbum(Model model, HttpServletRequest request) {
        logger.info("addAlbum called");
        model.addAttribute("albumViewModel", new AlbumViewModel());
        model.addAttribute("authors", authorService.getAuthors().stream().map(Author::getName).toArray());
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));

        return new ModelAndView("/album/addalbum", "genres", Genre.values());
    }

    @PostMapping("/addalbum")
    public ModelAndView processAddAlbum(@Valid @ModelAttribute("albumViewModel") AlbumViewModel albumViewModel, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("addAlbum called");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.warning(error.toString()));
            return new ModelAndView("/album/addalbum");
        }
        LocalDateTime localDateTime = LocalDateTime.of(parseInt(albumViewModel.getYear()), 1, 1, 0, 0);
        Genre genreValue = Genre.valueOf(albumViewModel.getGenre());
        Album album = new Album();
        album.setName(albumViewModel.getName());
        album.setYear(localDateTime);
        album.setGenre(genreValue);
        album.setAuthor(authorService.getAuthors().stream().filter(author -> author.getName().equals(albumViewModel.getAuthor())).findFirst().get());
        albumService.addAlbum(album);
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("redirect:/albums");
    }

    @GetMapping("/{album}")
    public ModelAndView showAlbum(@PathVariable String album, HttpServletRequest request) {
        logger.info("showAlbum called");
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        ModelAndView modelAndView = new ModelAndView("/album/album-details");
        Album albumObject = albumService.getAlbums().stream().filter(a -> a.getName().equals(album)).findFirst().get();
        AlbumViewModel albumViewModel = new AlbumViewModel();
        albumViewModel.setName(albumObject.getName());
        albumViewModel.setYear(albumObject.getYear() + "");

        modelAndView.addObject("album", albumObject);
        modelAndView.addObject("authors", authorService.getAuthors());
        modelAndView.addObject("genres", Genre.values());
        modelAndView.addObject("albumViewModel", albumViewModel);
        return modelAndView;
    }

    @PutMapping("/{album}")
    public ModelAndView editAlbum(@Valid @ModelAttribute("albumViewModel") AlbumViewModel albumViewModel, @PathVariable String album, HttpServletRequest request, BindingResult bindingResult) {
        logger.info("editAlbum called");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.warning(error.toString()));
            return new ModelAndView("/album/album-details");
        }
        Album originalAlbum = albumService.getAlbums().stream().filter(a -> a.getName().equals(album)).findFirst().get();
        Album newAlbum = originalAlbum;
        newAlbum.setName(albumViewModel.getName());
        newAlbum.setYear(LocalDateTime.of(parseInt(albumViewModel.getYear()), 1, 1, 0, 0));
        newAlbum.setGenre(Genre.valueOf(albumViewModel.getGenre()));
        albumViewModel.getAuthor();
        newAlbum.setAuthor(authorService.getAuthors().stream().filter(author -> author.getName().equals(albumViewModel.getAuthor())).findFirst().get());
        albumService.updateAlbum(originalAlbum, newAlbum);
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("redirect:/albums");
    }

    @DeleteMapping("/{album}")
    public ModelAndView deleteAlbum(@PathVariable String album, HttpServletRequest request) {
        logger.info("deleteAlbum called");
        logger.info(album);
        Album albumObject = albumService.getAlbums().stream().filter(album1 -> album1.getName().equals(album)).findFirst().get();
        albumService.removeAlbum(albumObject);
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("redirect:/albums");
    }

    @GetMapping
    public ModelAndView getAlbums(HttpServletRequest request) {
        logger.info("getAlbums called");
        historyController.addPageVisit(new PageVisit(request.getRequestURL().toString()));
        return new ModelAndView("/album/albums", "albums", albumService.getAlbums());
    }
}
