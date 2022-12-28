package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Song;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("collections")
@Repository
public class ListSongRepository implements SongRepository {
    static List<Song> songs = new ArrayList<>();

    @Override
    public List<Song> readSongs() {
        return songs;
    }

    @Override
    public Song createSong(Song song) {
        songs.add(song);
        return song;
    }

    @Override
    public void deleteSong(Song song) {
        songs.remove(song);
    }

    @Override
    public void updateSong(Song song, Song newSong) {
        songs.set(songs.indexOf(song), newSong);
//        List<Song> newSongs = new ArrayList<>(songs);
//        newSongs.remove(song);
//        newSongs.add(newSong);
//        songs = newSongs;
    }


}
