package com.wangf.spring.repository.mongo;

import com.wangf.spring.entity.mongo.Book;
import com.wangf.spring.repository.common.AbstractBookRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BookMongoRepositoryImpl extends AbstractBookRepositoryImpl<Book> {

    private final MongoTemplate mongoTemplate;

    public BookMongoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    protected List<Book> doFindAll() {
        Query query = new Query();
        return mongoTemplate.find(query, Book.class);
    }

    @Override
    protected Optional<Book> doFindByIsbn(String isbn) {
        Query query = new Query(Criteria.where("isbn").is(isbn)); // Query for specific ISBN
        Book book = mongoTemplate.findOne(query, Book.class);
        return Optional.ofNullable(book);
    }

    @Override
    public Book insert(Book book) {
        mongoTemplate.save(book); // Use save method to insert or update
        return book;
    }

}
