package com.practice.mongodb.controller;

import com.practice.mongodb.services.BookService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;
    @Mock
    private  BookService bookService;

    @BeforeAll
    public static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {

    }


    @AfterAll
    public static void AfterAll() {

    }

    @AfterEach
    public void AfterEach() {

    }

    @Test
    public void getAllBooks() {
        Mockito.when(bookService.getAllBooks()).thenReturn(new ArrayList<>());
        bookController.getAllBooks();
    }
}
