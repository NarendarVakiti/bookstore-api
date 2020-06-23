package com.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bean.Book;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT book FROM Book book WHERE book.author = (SELECT authorId FROM Author where authorName = ?1)")
    public List<Book> findBooksByAuthor(String authorName);

    @Query("SELECT book FROM Book book WHERE book.rating = ?1")
    public List<Book> findBooksByRating(String rating);


}
