package com.wangf.spring.service;

import com.wangf.spring.entity.jdbc.Book;
import com.wangf.spring.event.BookEvents;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("jdbc")
@Service
public class JdbcBookService extends AbstractBookService<Book> {
    public JdbcBookService(BookCachingRepository<Book> bookRepository, BookEvents bookEvents) {
        super(bookRepository, bookEvents);
    }
}
