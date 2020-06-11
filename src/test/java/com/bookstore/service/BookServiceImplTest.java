package com.bookstore.service;

import com.bookstore.bean.Author;
import com.bookstore.bean.BookRequest;
import com.bookstore.bean.Book;
import com.bookstore.dao.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getBookDetails() {
        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(bookDetails());
        when(bookRepository.findAll()).thenReturn(listOfBooks);
        List<Book> books = bookService.getBookDetails();
        assertEquals(listOfBooks, books);
    }

    @Test
    void saveBookDetails() {
        Book books = bookDetails();
        when(bookRepository.save(books)).thenReturn(books);
        assertEquals(books, bookService.saveBookDetails(bookRequest()));
    }

    @Test
    void findBooksByAuthor() {
        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(bookDetails());
        when(bookRepository.findBooksByAuthor("Kathy Sierra")).thenReturn(listOfBooks);
        assertEquals(listOfBooks, bookService.findBooksByAuthor("Kathy Sierra"));
    }

    @Test
    void deleteBookDetails() {
        Book books = bookDetails();
        when(bookRepository.existsById(books.getBookId())).thenReturn(true);
        //assertFalse(bookRepository.exists(books.getBookId()));
    }

    public Book bookDetails(){
        Book books = new Book();
        books.setBookId(2);
        books.setBookName("Head First Java");
        books.setAvailability(true);
        books.setBookPrice(450.00);
        books.setRating("4.3");
        books.setPublishedDate(LocalDate.of(2003, 4, 12));
        books.setLevel("Beginner");
        Author author = new Author();
        author.setAuthorName("Kathy Sierra");
        books.setAuthor(author);
        return books;
    }

    public BookRequest bookRequest(){
        BookRequest books = new BookRequest();
        books.setBookId(2);
        books.setBookName("Head First Java");
        books.setAvailability(true);
        books.setBookPrice(450.00);
        books.setRating("4.3");
        books.setPublishedDate(LocalDate.of(2003, 4, 12));
        books.setLevel("Beginner");
        books.setAuthorName("Kathy Sierra");
        return books;
    }
}