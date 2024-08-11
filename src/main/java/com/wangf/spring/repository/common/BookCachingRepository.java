package com.wangf.spring.repository.common;

import com.wangf.spring.entity.Book;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookCachingRepository extends CrudRepository<Book, String> {
    String BOOKS_CACHE = "books";

    @Override
    @Cacheable(value = BOOKS_CACHE)
    List<Book> findAll();

    @Override
    @Cacheable(value = BOOKS_CACHE, key = "#isbn")
    Optional<Book> findById(String isbn);


    @Override
    @CacheEvict(value = BOOKS_CACHE, key = "#isbn")
    void deleteById(String isbn);

    @Override
    @CachePut(value = BOOKS_CACHE, key = "#book.isbn")
    Book save(Book book);

}
