package com.musicdatabase.service.repository;

import com.musicdatabase.service.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositorySpring extends JpaRepository<Author, Long> {

}
