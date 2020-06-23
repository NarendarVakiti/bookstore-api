package com.bookstore.controller;

import com.bookstore.bean.Author;
import com.bookstore.bean.BookRequest;
import com.bookstore.bean.Book;
import com.bookstore.bean.Status;
import com.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getBookDetails() throws Exception {
        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(bookDetails());
        when(bookService.getBookDetails()).thenReturn(listOfBooks);

        MvcResult mvcResult = mockMvc.perform(get("/getbookdetails")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void addBookDetails() throws Exception {
        Status status = new Status("Book details are added successfully", true);
        String response = (new ObjectMapper()).writeValueAsString(status);
        when(bookService.saveBookDetails(bookRequest())).thenReturn(response);
        String jsonRequest = (new ObjectMapper()).writeValueAsString(bookRequest());
        mockMvc.perform(post("/addbookdetails").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    void findBooksByAuthor() throws Exception {
        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(bookDetails());
        when(bookService.findBooksByAuthor("Kathy Sierra")).thenReturn(listOfBooks);

        MvcResult mvcResult = mockMvc.perform(get("/findbooksbyauthor/authorName","Kathy Sierra")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void deleteBookDetails() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/deletebookdetails/bookId","2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 405);
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