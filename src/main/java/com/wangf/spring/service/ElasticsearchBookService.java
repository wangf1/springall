package com.wangf.spring.service;

import com.wangf.spring.entity.elasticsearch.Book;
import com.wangf.spring.event.BookEvents;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("elasticsearch")
@Service
public class ElasticsearchBookService extends AbstractBookService<Book> {
    public ElasticsearchBookService(BookCachingRepository<Book> bookRepository, BookEvents bookEvents) {
        super(bookRepository, bookEvents);
    }
}
