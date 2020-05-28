package com.bookstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bean.Author;
import com.bookstore.bean.BookDetails;
import com.bookstore.bean.BookStore;
import com.bookstore.service.BookService;

/**
 * @author nvakiti
 *
 */
@RestController
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	
	@Autowired
	private BookService bookService;
	
	/**
	 * To get all book details
	 * @return books 
	 */
	@GetMapping("/getbookdetails")
	public List<BookStore> getBookDetails(){
		List<BookStore> books = bookService.getBookDetails();
		return books;
	}
	
	/**
	 * To get all book authors details
	 * @return authors
	 */
	@GetMapping("/getauthordetails")
	public List<Author> getAuthorDetails(){
		List<Author> authors = bookService.getAuthorDetails();
		return authors;
	}
	
	/**
	 * Store book and author details
	 * @param bookDetails
	 * @return message
	 */
	@PostMapping("/addbookdetails")
	public String addBookDetails(@RequestBody BookDetails bookDetails) {
		String message = bookService.saveBookDetails(bookDetails);
		return message;
	}
	
}
