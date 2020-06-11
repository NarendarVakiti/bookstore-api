package com.bookstore.service;

import java.util.List;

import com.bookstore.bean.BookRequest;
import com.bookstore.bean.Book;

public interface BookService {
	
	public List<Book> getBookDetails();
	public String saveBookDetails(BookRequest bookRequest);
	public List<Book> findBooksByAuthor(String authorName);
	public void deleteBookDetails(Integer bookId);
	public Book updateBookDetails(Book book);
	public Boolean existsBookById(Integer bookId);
	public List<Book> findBooksByRating(String rating);
	
}
