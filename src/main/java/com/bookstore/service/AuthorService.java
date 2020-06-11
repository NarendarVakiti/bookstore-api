package com.bookstore.service;

import com.bookstore.bean.Author;
import com.bookstore.bean.AuthorRequest;

import java.util.List;

public interface AuthorService {
	
	public List<Author> getAuthorDetails();
	public String saveAuthorDetails(AuthorRequest authorRequest);
	public Author findAuthorByName(String authorName);
	public void deleteAuthorDetails(Integer authorId);
	public Author updateAuthorDetails(Author author);
}
