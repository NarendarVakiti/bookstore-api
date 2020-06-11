package com.bookstore.controller;

import com.bookstore.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class AuthorControllerTest {

    @Mock
    private AuthorService AuthorService;

    @InjectMocks
    private AuthorController authorController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    void addAuthorDetails() {
    }

    @Test
    void getAuthorDetails() {
    }

    @Test
    void deleteAuthorDetails() {
    }
}