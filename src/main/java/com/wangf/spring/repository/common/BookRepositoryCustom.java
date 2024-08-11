package com.wangf.spring.repository.common;

import com.wangf.spring.entity.Book;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryCustom {
    @Cacheable(value = BookCachingRepository.BOOKS_CACHE, key = "'ALL_BOOKS'")
    List<Book> findAllSlow();

    @Cacheable(value = BookCachingRepository.BOOKS_CACHE, key = "#isbn")
    Optional<Book> findOneSlow(String isbn);

    @CachePut(value = BookCachingRepository.BOOKS_CACHE, key = "#book.isbn")
    Book insert(Book book);
}
