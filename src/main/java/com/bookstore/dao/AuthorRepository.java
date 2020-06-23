package com.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bean.Author;
import org.springframework.data.jpa.repository.Query;


public interface AuthorRepository extends JpaRepository<Author, Integer>{

    @Query("SELECT a FROM Author a WHERE a.authorName = ?1")
    Author findAuthorByName(String authorName);

}
