package com.practice.mongodb.services;

import com.practice.mongodb.dto.BookDTO;
import com.practice.mongodb.entity.Book;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO createBook(Book book);

    List<BookDTO> getBooksByFilters(Integer id, String name, String author,
                                           Integer cost, Integer from, Integer to,
                                           String filterType);

    String getNameById(Integer id);
}
