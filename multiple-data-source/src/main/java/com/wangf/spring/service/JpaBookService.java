package com.wangf.spring.service;

import com.wangf.spring.entity.jpa.Book;
import com.wangf.spring.event.BookEvents;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("jpa")
@Service
public class JpaBookService extends AbstractBookService<Book> {
    public JpaBookService(BookCachingRepository<Book> bookRepository, BookEvents bookEvents) {
        super(bookRepository, bookEvents);
    }
}
