package com.wangf.spring.service;

import com.wangf.spring.entity.mongo.Book;
import com.wangf.spring.event.BookEvents;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("mongo")
@Service
public class MongoBookService extends AbstractBookService<Book> {
    public MongoBookService(BookCachingRepository<Book> bookRepository, BookEvents bookEvents) {
        super(bookRepository, bookEvents);
    }
}
