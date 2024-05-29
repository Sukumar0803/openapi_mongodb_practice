package com.practice.mongodb.services;

import com.practice.mongodb.dto.BookDTO;
import com.practice.mongodb.entity.Book;
import com.practice.mongodb.exception.BookAlreadyFoundException;
import com.practice.mongodb.exception.BookNotFoundException;
import com.practice.mongodb.mapper.BookMapper;
import com.practice.mongodb.repository.BookRepository;
import com.practice.mongodb.repository.BookRepositoryMongoDBTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookRepositoryMongoDBTemplate bookRepositoryMongoDBTemplate;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, BookRepositoryMongoDBTemplate bookRepositoryMongoDBTemplate) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookRepositoryMongoDBTemplate = bookRepositoryMongoDBTemplate;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        try {
            List<Book> books = new ArrayList<>();
            List<CompletableFuture<List<Book>>> futures = new ArrayList<>();
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (int i : IntStream.range(1,10).toArray()) {
                CompletableFuture<List<Book>> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        if (i == 9 || i == 3 || i==1)
                            throw new RuntimeException("exception for ith Run");
                        return bookRepository.findAll();
                    } catch (Exception e) {
                        System.out.println("An Exception occurered due to "+ e.getMessage());
                        return null;
                    }
                },executorService);
                futures.add(future);
            }
            futures = futures.stream().filter(fut -> !fut.isCompletedExceptionally()).collect(Collectors.toList());
            for (CompletableFuture<List<Book>> future : futures) {
                if (!Objects.isNull(future.get())) {
                    books.addAll(future.join());
                }
            }
            List<BookDTO> booksResponse = new ArrayList<>();
            books.forEach(b -> booksResponse.add(bookMapper.getBookDTO(b)));
            return booksResponse;
        } catch (Exception e) {
            throw new RuntimeException("An Exception occurred due to " + e.getMessage());
        }
    }

    @Override
    public BookDTO createBook(Book book) {

        Optional<Book> bookDetails = bookRepository.findById(book.getId());
        if (bookDetails.isPresent())
            throw new BookAlreadyFoundException(String.format("Book: %s Already Exists in Database", book.getId()));
        try {
            Book createdBook = bookRepository.save(book);
            return bookMapper.getBookDTO(createdBook);
        } catch (Exception e) {
            throw new RuntimeException("An Exception occurred due to " + e.getMessage());
        }
    }

    @Override
    public List<BookDTO> getBooksByFilters(Integer id, String name, String author, Integer cost, Integer from, Integer to, String filterType) {
        try {
            List<Book> books = bookRepositoryMongoDBTemplate.findByFilters(id, name, author, cost, from, to, filterType);
            List<BookDTO> booksResponse = new ArrayList<>();
            books.forEach(b -> booksResponse.add(bookMapper.getBookDTO(b)));
            return booksResponse;
        } catch (Exception e) {
            throw new RuntimeException("An Exception occurred due to " + e.getMessage());
        }

    }

    @Override
    public String getNameById(Integer id) {
        Book book ;
        try {
            book = bookRepository.findNameById(id);
        } catch (Exception e) {
            throw new RuntimeException("An Exception occurred due to " + e.getMessage());
        }
        if (Optional.ofNullable(book).isPresent())
            return book.getName();
        throw new BookNotFoundException("Not Found for Id:" + id);
    }
}
