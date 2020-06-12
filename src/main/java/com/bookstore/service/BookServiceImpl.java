package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bookstore.bean.*;
import com.bookstore.dao.AdminRepository;
import com.bookstore.exceptionhandling.BookNotExisted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.AuthorRepository;
import com.bookstore.dao.BookRepository;
import com.google.gson.Gson;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AdminRepository adminRepository;

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	
	/**
	 * To get all book details
	 * @return books 
	 */
	@Override
	public List<Book> getBookDetails() {
		
		List<Book> listOfBooks = new ArrayList<>();
		try {
			listOfBooks = bookRepository.findAll();
			listOfBooks = listOfBooks.stream().sorted().collect(Collectors.toList());
			logger.info("Book Details Response :: "+listOfBooks);
			
		} catch (Exception e) {
			logger.error("Exception While Fetching Book Details :: "+e.getMessage());
		}
		return listOfBooks;
	}

	/**
	 * Store book and author details
	 * @param book
	 * @return message
	 */
	@Override
	public String saveBookDetails(BookRequest book) {
		String message = null;
		Status status = new Status("Book details are not saved successfully", false);
		try {
			boolean bookFlag = Optional.ofNullable(book).isPresent();
			if(bookFlag) {
				logger.info("Book Details save request :: "+book);
				Author author = authorRepository.findAuthorByName(book.getAuthorName());
				Book bookStore = new Book();
				bookStore.setBookName(book.getBookName());
				bookStore.setBookPrice(book.getBookPrice());
				bookStore.setAvailability(book.isAvailability());
				bookStore.setLevel(book.getLevel());
				bookStore.setRating(book.getRating());
				if(author == null){
					logger.info("Author is not existed with name :: "+book.getAuthorName());
				}else{
					bookStore.setAuthor(author);
				}
				Admin admin = adminRepository.findAdminByName(book.getAddedBy());
				if(author == null){
					logger.info("Admin is not existed with name :: "+book.getAddedBy());
				}else{
					bookStore.setAdmin(admin);
				}
				Book books = bookRepository.save(bookStore);
				status = new Status("Book details are added successfully", true);
				if(books == null){
					status = new Status("Book details are not added successfully, try again", true);
				}
			}
			message = new Gson().toJson(status);
			
		}
		catch (Exception e) {
			logger.error("Exception While Saving Book Details :: "+e);
		}
		return message;
	}

	@Override
	public List<Book> findBooksByAuthor(String authorName) {
		List<Book> books = new ArrayList<>();
		try{
			books = bookRepository.findBooksByAuthor(authorName);
		}catch(Exception e){
			logger.error("Exception While fetching Books Details by author name :: "+e.getMessage());
			throw e;
		}
		return books;
	}

	@Override
	public void deleteBookDetails(Integer bookId){
		try{
			bookRepository.deleteById(bookId);
		}catch(Exception e){
			logger.info("Exception while deleting book details :: "+e.getMessage());
			throw e;
		}
	}

	@Override
	public Boolean existsBookById(Integer bookId){
		return bookRepository.existsById(bookId);
	}

	@Override
	public Book updateBookDetails(Book book){
		Book updatedBook = new Book();
		try{
			updatedBook = bookRepository.save(book);
		}catch (Exception e){
			logger.info("Exception while updating book details :: "+e.getMessage());
		}
		return book;
	}

	@Override
	public List<Book> findBooksByRating(String rating){
		List<Book> books = new ArrayList<>();
		try{
			books = bookRepository.findBooksByRating(rating);

			//find availability of books
			if(!books.isEmpty()){
				books = books.stream().filter(book -> book.isAvailability()).collect(Collectors.toList());
			}
		}catch (Exception e){
			logger.info("Exception while updating book details :: "+e.getMessage());
		}
		return books;
	}
}
