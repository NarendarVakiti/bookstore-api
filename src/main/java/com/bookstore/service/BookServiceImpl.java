package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bean.Author;
import com.bookstore.bean.BookDetails;
import com.bookstore.bean.BookStore;
import com.bookstore.bean.Status;
import com.bookstore.dao.AuthorDao;
import com.bookstore.dao.BookStoreDao;
import com.google.gson.Gson;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookStoreDao bookStoreDao;
	
	@Autowired
	private AuthorDao authorDao;
	
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	
	/**
	 * To get all book details
	 * @return books 
	 */
	@Override
	public List<BookStore> getBookDetails() {
		
		List<BookStore> listOfBooks = null;
		try {
			listOfBooks = bookStoreDao.findAll();
			logger.info("Book Details Response :: "+listOfBooks);
			
		} catch (Exception e) {
			logger.error("Exception While Fetching Book Details :: "+e);
		}
		return listOfBooks;
	}

	/**
	 * To get all book authors details
	 * @return authors
	 */
	@Override
	public List<Author> getAuthorDetails() {
		List<Author> author = null;
		try {
			author = authorDao.findAll();
			logger.info("Author Details Response :: "+author);
		} catch (Exception e) {
			logger.error("Exception While Fetching Author Details :: "+e);
		}
		return author;
	}

	/**
	 * Store book and author details
	 * @param book
	 * @return message
	 */
	@Override
	public String saveBookDetails(BookDetails book) {
		String message = null;
		Status status = new Status("Book details are not saved successfully", false);
		try {
			boolean bookFlag = Optional.ofNullable(book).isPresent();
			if(bookFlag) {
				logger.info("Book Details save request :: "+book);
				Author author = new Author();
				author.setAuthorName(book.getAuthorName());
				author.setAddress(book.getAddress());
				Author authorId = authorDao.save(author);
				
				BookStore bookStore = new BookStore();
				bookStore.setBookName(book.getBookName());
				bookStore.setBookPrice(book.getBookPrice());
				bookStore.setAuthor(authorId);
				
				bookStoreDao.save(bookStore);
				status = new Status("Book details are added successfully", true);
				
			}
			message = new Gson().toJson(status);
			
		}
		catch (Exception e) {
			logger.error("Exception While Saving Book Details :: "+e);
		}
		return message;
	}

}
