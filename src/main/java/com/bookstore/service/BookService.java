package com.bookstore.service;

import java.util.List;

import com.bookstore.bean.Author;
import com.bookstore.bean.BookDetails;
import com.bookstore.bean.BookStore;

public interface BookService {
	
	public List<BookStore> getBookDetails();
	public List<Author> getAuthorDetails();
	public String saveBookDetails(BookDetails bookDetails);
	
}
