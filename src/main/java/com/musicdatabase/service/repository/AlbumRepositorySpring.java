package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Profile("spring")
public interface AlbumRepositorySpring extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a WHERE a.name = ?1")
    List<Album> findByName(String name);

    @Override
    List<Album> findAll();

    @Query("SELECT a FROM Album a WHERE a.name = ?1 and a.year = ?2")
    List<Album> findByNameAndYear(String title, int year);
}
