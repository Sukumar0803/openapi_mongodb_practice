package com.practice.mongodb.mapper;

import com.practice.mongodb.dto.BookDTO;
import com.practice.mongodb.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book getBookEntity(BookDTO book) {
         return Book.builder().id(book.getId())
                .pages(book.getPages())
                .cost(book.getCost())
                .name(book.getName())
                .author(book.getAuthor()).build();
    }

    public BookDTO getBookDTO(Book book) {
        return BookDTO.builder().id(book.getId())
                .pages(book.getPages())
                .cost(book.getCost())
                .name(book.getName())
                .author(book.getAuthor()).build();
    }
}
