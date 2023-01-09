package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("spring")
public interface AlbumRepositorySpring extends JpaRepository<Album, Long> {

}
