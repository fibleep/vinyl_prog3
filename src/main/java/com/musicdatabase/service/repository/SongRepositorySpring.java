package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Song;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Profile("spring")
public interface SongRepositorySpring extends JpaRepository<Song, Long> {
    @Override
    List<Song> findAll();


    Song findByTitle(String title);

    @Query("SELECT s FROM Song s WHERE s.album.name = ?1")
    List<Song> findByAlbumName(String album);

}
