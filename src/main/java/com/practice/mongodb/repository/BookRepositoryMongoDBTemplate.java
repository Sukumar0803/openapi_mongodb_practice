package com.practice.mongodb.repository;

import com.practice.mongodb.entity.Book;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryMongoDBTemplate {
    private final MongoTemplate mongoTemplate;

    public BookRepositoryMongoDBTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public List<Book> findByFilters(Integer id, String name, String author, Integer cost, Integer from, Integer to, String filterType) {
        Query query = new Query();

        if (id != null) {
            query.addCriteria(Criteria.where("id").is(id));
        }
        if (name != null) {
            query.addCriteria(Criteria.where("name").is(name));
        }
        if (author != null) {
            query.addCriteria(Criteria.where("author").is(author));
        }
        if (cost != null) {
            query.addCriteria(Criteria.where("cost").is(cost));
        }
        if (from != null && to != null) {
            if ("id".equals(filterType)) {
                query.addCriteria(Criteria.where("id").gte(from).lte(to));
            } else {
                query.addCriteria(Criteria.where("cost").gte(from).lte(to));
            }
        }
        return mongoTemplate.find(query, Book.class);
    }
}