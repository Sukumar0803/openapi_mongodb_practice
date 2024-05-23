package com.practice.mongodb.repository;

import com.practice.mongodb.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookRepository extends MongoRepository<Book, Integer> {
    @Query(value = "{'id' : ?0}", fields = "{'name': 1}")
    Book findNameById(Integer id);
}
