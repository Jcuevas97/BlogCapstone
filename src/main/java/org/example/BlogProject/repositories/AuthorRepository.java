package org.example.BlogProject.repositories;

import org.example.BlogProject.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
    @Query(value = "SELECT * FROM Author WHERE author_email = ?1", nativeQuery = true)
    public Author findByEmail(String email);
}