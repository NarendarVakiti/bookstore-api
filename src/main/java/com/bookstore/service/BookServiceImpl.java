package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bookstore.bean.Author;
import com.bookstore.bean.BookDetails;
import com.bookstore.bean.BookStore;
import com.bookstore.bean.Status;
import com.bookstore.controller.BookController;
import com.google.gson.Gson;

@Service
public class BookServiceImpl implements BookService{
	
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	List<BookStore> books = new ArrayList<>();
	List<Author> author = new ArrayList<>();
	
	Author a1 = new Author(1011, "Herbert Schildt", "USA");
	Author a2 = new Author(1012, "Kathy Sierra", "India");
	Author a3 = new Author(1013, "Jamie Chan", "Australia");
	Author a4 = new Author(1014, "Joshua Bloch", "Japan");
	
	/**
	 * To get all book details
	 * @return books 
	 */
	@Override
	public List<BookStore> getBookDetails() {
		logger.info("Started getBookDetails()");
		try {
			books.add(new BookStore(101, "Head First Java", 500.00, true, a1));
			books.add(new BookStore(102, "Java: A Beginner's Guide", 1000.00, true, a2));
			books.add(new BookStore(103, "Learn Java in One Day and Learn It Well", 750.00, true, a3));
			books.add(new BookStore(104, "Effective Java", 500.00, true, a4));
			books.add(new BookStore(105, "Head First Servlets and JSP", 480.00, true, a2));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("End getBookDetails()");
		return books;
	}

	/**
	 * To get all book authors details
	 * @return authors
	 */
	@Override
	public List<Author> getAuthorDetails() {
		logger.info("Started getAuthorDetails()");
		try {
			author.add(a1);
			author.add(a2);
			author.add(a3);
			author.add(a4);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("Started getAuthorDetails()");
		return author;
	}

	/**
	 * Store book and author details
	 * @param bookDetails
	 * @return message
	 */
	@Override
	public String saveBookDetails(BookDetails bookDetails) {
		logger.info("Started saveBookDetails()");
		String message = null;
		Status status = new Status("Book details are not saved successfully", false);
		try {
			boolean bookFlag = Optional.ofNullable(bookDetails).isPresent();
			if(bookFlag) {
				int bookId = bookDetails.getBookId();
				String bookName = bookDetails.getBookName();
				double price = bookDetails.getBookPrice();
				boolean flag = bookDetails.isActive();
				int authId = bookDetails.getAuthorId();
				String authName = bookDetails.getAuthorName();
				String authadd = bookDetails.getAddress();
				
				Author a5 = new Author(authId, authName, authadd);
				author.add(a5);
				
				books.add(new BookStore(bookId, bookName, price, flag, a5));
				status = new Status("Book details are added successfully", true);
				
			}
			message = new Gson().toJson(status);
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("Started saveBookDetails()");
		return message;
	}

}
