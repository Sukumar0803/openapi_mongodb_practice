package com.practice.mongodb.services;

import com.practice.mongodb.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private  BookRepository bookRepository;

    @Test
    public void getAllBooks() {
        Mockito.when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        bookService.getAllBooks();
    }

}
