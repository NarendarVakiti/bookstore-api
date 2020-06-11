package com.bookstore.service;

import com.bookstore.bean.*;
import com.bookstore.dao.AddressRepository;
import com.bookstore.dao.AuthorRepository;
import com.bookstore.dao.BookRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	/**
	 * To get all book authors details
	 * @return authors
	 */
	@Override
	public List<Author> getAuthorDetails() {
		List<Author> author = new ArrayList<>();
		try {
			author = authorRepository.findAll();
			logger.info("Author Details Response :: "+author);
		} catch (Exception e) {
			logger.error("Exception While Fetching Author Details :: "+e.getMessage());
		}
		return author;
	}

	/**
	 * Store book and author details
	 * @param request
	 * @return message
	 */
	@Override
	public String saveAuthorDetails(AuthorRequest request) {
		String message = null;
		Status status = new Status("Author details are not saved successfully", false);
		try {
			boolean bookFlag = Optional.ofNullable(request).isPresent();
			if(bookFlag) {
				logger.info("Author Details save request :: "+request);
				Address address = addressRepository.save(request.getAuthor().getAddress());
				Author authorId = authorRepository.save(request.getAuthor());
				status = new Status("Author details are added successfully", true);
				
			}
			message = new Gson().toJson(status);
			
		}
		catch (Exception e) {
			logger.error("Exception While Saving Author Details :: "+e.getMessage());
		}
		return message;
	}

	@Override
	public Author findAuthorByName(String authorName){
		Author author = new Author();
		try{
			author = authorRepository.findAuthorByName(authorName);
		}catch(Exception e){
			logger.info("Exception while fetching Author details by author name :: "+e.getMessage());
		}
		return author;
	}

	@Override
	public void deleteAuthorDetails(Integer auhtorId){
		try{
			authorRepository.deleteById(auhtorId);
		}catch(Exception e){
			logger.info("Exception while deleting author details :: "+e.getMessage());
		}
	}

	public Author updateAuthorDetails(Author author){
		Author auth = new Author();
		try{
			if(author != null){
				auth = authorRepository.save(author);
			}
		}catch (Exception e){
			logger.info("Exception while updating author details :: "+e.getMessage());
		}
		return auth;
	}

}
