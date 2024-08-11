package com.wangf.spring.repository.common;

import com.wangf.spring.entity.Book;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


@NonNullApi
public interface BookCachingRepository extends CrudRepository<Book, String>, BookRepositoryCustom {


    String BOOKS_CACHE = "books";
    String ALL_BOOKS = "ALL_BOOKS";

    @Override
    @Cacheable(value = BOOKS_CACHE, key = "'" + ALL_BOOKS + "'")
    List<Book> findAll();

    @Override
    @Cacheable(value = BOOKS_CACHE, key = "#isbn")
    Optional<Book> findById(String isbn);


    @Override
    @CacheEvict(value = BOOKS_CACHE, key = "#isbn")
    void deleteById(String isbn);

    @Override
    @CachePut(value = BOOKS_CACHE, key = "#book.isbn")
    @SuppressWarnings("unchecked")
    Book save(Book book);

}
