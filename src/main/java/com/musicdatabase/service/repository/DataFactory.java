package com.musicdatabase.service.repository;



import com.musicdatabase.service.domain.Album;
import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.domain.Song;

import java.util.List;

public interface DataFactory {
    void seed();
    List<Song> getSongs();
    List<Author> getAuthors();
    List<Album> getAlbums();

}
