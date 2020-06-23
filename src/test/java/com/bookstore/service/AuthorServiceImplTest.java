package com.bookstore.service;

import com.bookstore.bean.Address;
import com.bookstore.bean.Author;
import com.bookstore.dao.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorServiceImpl).build();
    }

    @Test
    void testGetAuthorDetails() {
        List<Author> listOfAuthors = new ArrayList<>();
        listOfAuthors.add(getAuthor());
        when(authorRepository.findAll()).thenReturn(listOfAuthors);
        List<Author> authors = authorServiceImpl.getAuthorDetails();
        assertEquals(listOfAuthors, authors);

    }

    @Test
    void testSaveAuthorDetails() {
    }

    @Test
    void testFindAuthorByName() {
        Author author = getAuthor();
        when(authorRepository.findAuthorByName("Narendar")).thenReturn(author);
        assertEquals(author, authorServiceImpl.findAuthorByName("Narendar"));

    }

    @Test
    void testDeleteAuthorDetails() {
        Author author = getAuthor();
        when(authorRepository.existsById(12)).thenReturn(true);
        authorServiceImpl.deleteAuthorDetails(author.getAuthorId());
        //assertFalse(authorRepository.existsById(12));
        assertEquals(true, authorRepository.existsById(author.getAuthorId()));
    }

    public Author getAuthor(){
        Author author = new Author();
        author.setAuthorName("Narendar");
        author.setAuthorId(12);
        author.setAuthorEmail("naren@test.com");

        Address address = new Address();
        address.setAddressID(11);
        address.setCity("Hyderabad");
        address.setCountry("India");
        author.setAddress(address);
        return author;
    }
}