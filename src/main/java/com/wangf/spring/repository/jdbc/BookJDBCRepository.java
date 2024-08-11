package com.wangf.spring.repository.jdbc;

import com.wangf.spring.entity.Book;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@NonNullApi
public interface BookJDBCRepository extends CrudRepository<Book, String>, BookRepositoryCustom {
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


    // This method has no return value, so we should never use @CachePut
    // @CachePut(value = BOOKS_CACHE, key = "#book.isbn")
    @Modifying
    @Query("INSERT INTO book (isbn, title) VALUES (:#{#book.isbn}, :#{#book.title})")
    void insert(Book book);

}
