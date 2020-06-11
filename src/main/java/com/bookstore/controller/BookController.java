package com.bookstore.controller;

import java.util.List;

import com.bookstore.bean.*;
import com.bookstore.exceptionhandling.BookNotExisted;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public String getBookDetails(){
		String response = null;
		try{
			List<Book> books = bookService.getBookDetails();
			response = new Gson().toJson(books);
			if(books.isEmpty()){
				Status status = new Status("Books details not found",false);
				response = new Gson().toJson(status);
			}
			return response;
		}catch(Exception e){
			logger.error("Exception while fetching the book details : "+e.getMessage());
			Status status = new Status("Something went wrong while fetching the books details",false);
			response = new Gson().toJson(status);
			return response;
		}
	}
	
	/**
	 * Store book and author details
	 * @param bookRequest
	 * @return message
	 */
	@PostMapping("/addbookdetails")
	public String addBookDetails(@RequestBody BookRequest bookRequest) {
		String message = bookService.saveBookDetails(bookRequest);
		return message;
	}

	@GetMapping("/findbooksbyauthor/{authorName}")
	public String findBooksByAuthor(@PathVariable String authorName){
		String response = null;
		try{
			List<Book> books = bookService.findBooksByAuthor(authorName);
			response = new Gson().toJson(books);
			if(books.isEmpty()){
				Status status = new Status("Books details not found for author :: "+authorName,false);
				response = new Gson().toJson(status);
			}
			return response;
		}catch(Exception e){
			logger.error("Exception while fetching the book details : "+e.getMessage());
			Status status = new Status("Something went wrong while fetching the book details by author name",false);
			response = new Gson().toJson(status);
			return response;
		}
	}

	@DeleteMapping("/deletebookdetails/{bookId}")
	public String deleteBookDetails(@PathVariable Integer bookId){
		try{
			boolean flag = bookService.existsBookById(bookId);
			if(flag){
				bookService.deleteBookDetails(bookId);
			}
			else {
				throw new BookNotExisted("Book Details not found for Book ID ::"+bookId,
						"Book ID :: "+bookId+" details are not deleted");
			}
		}catch(Exception e){
			logger.info("Exception while deleting the book details :: "+e.getMessage());
			return "Exception while deleting the book details";
		}
		return "Book Id :: "+bookId+" details are deleted successfully";
	}

	@PutMapping("/updatebookdetails")
	public Book updateBookDetails(@RequestBody Book updateBook){
		Book book = new Book();
		try{
			book = bookService.updateBookDetails(updateBook);

		}catch(Exception e){
			logger.info("Exception while updating book details :: "+e.getMessage());
		}
		return book;
	}

	@GetMapping("/findBooksByRating/{rating}")
	public String findBooksByRating(@PathVariable String rating){
		String response = null;
		try{
			List<Book> books = bookService.findBooksByRating(rating);
			if(!books.isEmpty()){
				response = new Gson().toJson(books);
			}
			else{
				logger.info("Books Details not found on rating :: "+rating);
				Status status = new Status("Books Details not found on rating :: "+rating,false);
				response = new Gson().toJson(status);
			}
		}catch(Exception e){
			logger.error("Exception while fetching the books details : "+e.getMessage());
			Status status = new Status("Something went wrong while fetching the books details",false);
			response = new Gson().toJson(status);
			return response;
		}
		return null;
	}
	
}
