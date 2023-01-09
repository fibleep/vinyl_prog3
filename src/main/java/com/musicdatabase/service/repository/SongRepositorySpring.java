package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepositorySpring extends JpaRepository<Song, Long> {

}
