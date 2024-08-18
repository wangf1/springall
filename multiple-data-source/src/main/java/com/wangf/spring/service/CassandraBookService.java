package com.wangf.spring.service;

import com.wangf.spring.entity.cassandra.Book;
import com.wangf.spring.event.BookEvents;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("cassandra")
@Service
public class CassandraBookService extends AbstractBookService<Book> {
    public CassandraBookService(BookCachingRepository<Book> bookRepository, BookEvents bookEvents) {
        super(bookRepository, bookEvents);
    }
}
