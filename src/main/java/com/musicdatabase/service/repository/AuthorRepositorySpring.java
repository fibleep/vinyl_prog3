package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Profile("spring")
public interface AuthorRepositorySpring extends JpaRepository<Author, Long> {
    @Override
    List<Author> findAll();

    @Query("SELECT a FROM Author a WHERE a.name = ?1")
    Author findByName(String name);

    @Query("SELECT a.author.name from Album a where a.name = ?1")
    Author findAuthorByAlbumName(String album);
}
