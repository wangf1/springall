package com.wangf.spring.repository.common;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryCustom<T> {
    @Cacheable(value = BookCachingRepository.BOOKS_CACHE, key = "'ALL_BOOKS'")
    List<T> findAllSlow();

    @Cacheable(value = BookCachingRepository.BOOKS_CACHE, key = "#isbn")
    Optional<T> findOneSlow(String isbn);

    @CachePut(value = BookCachingRepository.BOOKS_CACHE, key = "#book.isbn")
    T insert(T book);

}
