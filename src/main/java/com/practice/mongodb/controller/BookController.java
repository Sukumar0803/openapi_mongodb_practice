package com.practice.mongodb.controller;

import com.practice.mongodb.dto.BookDTO;
import com.practice.mongodb.entity.Book;
import com.practice.mongodb.mapper.BookMapper;
import com.practice.mongodb.services.BookService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookRequest) {
        Book book = bookMapper.getBookEntity(bookRequest);
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    /**
     * Just For Hateous Learning
     */
    @GetMapping("/filters")
    public CollectionModel<BookDTO> getBooksBasedOnFilters(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String author,
                                                           @RequestParam(required = false) Integer cost, @RequestParam(required = false) Integer from,
                                                           @RequestParam(required = false) Integer to,
                                                           @RequestParam(required = false) String filterType) {
        List<BookDTO> response = bookService.getBooksByFilters(id, name, author, cost, from, to, filterType);
        CollectionModel<BookDTO> model = CollectionModel.of(response);
        WebMvcLinkBuilder selfLink = linkTo(methodOn(this.getClass()).getAllBooks());
        model.add(selfLink.withSelfRel());
        return model;
    }

    @GetMapping("getname/{id}")
    public ResponseEntity<?> getNameById(@PathVariable Integer id) {
        String name = bookService.getNameById(id);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }


}
