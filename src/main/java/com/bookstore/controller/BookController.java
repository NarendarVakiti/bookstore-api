package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bean.Author;
import com.bookstore.bean.BookDetails;
import com.bookstore.bean.BookStore;
import com.bookstore.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/getbookdetails")
	public List<BookStore> getBookDetails(){
		return bookService.getBookDetails();
	}
	
	@GetMapping("/getauthordetails")
	public List<Author> getAuthorDetails(){
		return bookService.getAuthorDetails();
	}
	
	@PostMapping("/addbookdetails")
	public String addBookDetails(@RequestBody BookDetails bookDetails) {
		String msg = bookService.saveBookDetails(bookDetails);
		return msg;
	}
	
}
