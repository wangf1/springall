package com.wangf.spring.repository.common;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


@NonNullApi
public interface BookCachingRepository<T> extends CrudRepository<T, String>, BookRepositoryCustom<T> {


    String BOOKS_CACHE = "books";
    String ALL_BOOKS = "ALL_BOOKS";

    @Override
    @Cacheable(value = BOOKS_CACHE, key = "'" + ALL_BOOKS + "'")
    List<T> findAll();

    @Override
    @Cacheable(value = BOOKS_CACHE, key = "#isbn")
    Optional<T> findById(String isbn);


    @Override
    @CacheEvict(value = BOOKS_CACHE, key = "#isbn")
    void deleteById(String isbn);


    @Override
    @CachePut(value = BookCachingRepository.BOOKS_CACHE, key = "#entity.isbn")
    <S extends T> S save(S entity);
}
