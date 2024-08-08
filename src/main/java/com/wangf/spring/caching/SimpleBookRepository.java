package com.wangf.spring.caching;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleBookRepository implements BookRepository {

    public static final String BOOKS_CACHE = "books";
    private final CacheManager cacheManager;
    private final List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("ISBN-1", "The Stand"),
            new Book("ISBN-2", "The Shining"),
            new Book("ISBN-3", "The Stand"),
            new Book("ISBN-4", "The Shining")
    ));


    @Override
    @CachePut(value = BOOKS_CACHE, key = "#book.isbn")
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    @Override
    @CachePut(value = BOOKS_CACHE, key = "#book.isbn")
    public Book updateBook(Book book) {
        books.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).
                findFirst().ifPresent(b -> b.setTitle(book.getTitle()));
        return book;
    }

    @Override
    @CacheEvict(value = BOOKS_CACHE, key = "#isbn")
    public Book deleteBook(String isbn) {
        Book book = books.stream().filter(b -> b.getIsbn().equals(isbn)).
                findFirst().orElse(null);
        books.removeIf(b -> b.getIsbn().equals(isbn));
        return book;
    }


    @Override
    @Cacheable(value = BOOKS_CACHE)
    public List<Book> getAllBooks() {
        return executeWithTiming(() -> {
            simulateSlowService();
            putEveryBookIntoCache(books);
            return books;
        });
    }

    private void putEveryBookIntoCache(List<Book> books) {
        Cache cache = cacheManager.getCache(BOOKS_CACHE);
        if (cache != null) {
            books.forEach(book -> cache.put(book.getIsbn(), book));
        }
    }

    @Override
    @Cacheable(value = BOOKS_CACHE, key = "#isbn")
    public Book getByIsbn(String isbn) {
        return executeWithTiming(() -> {
            simulateSlowService();
            return books.stream().filter(book -> book.getIsbn().equals(isbn)).
                    findFirst().orElse(null);
        });
    }


    private <T> T executeWithTiming(Supplier<T> supplier) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return supplier.get();
        } finally {
            stopWatch.stop();
            log.info("Time elapsed: {}", stopWatch.getTotalTimeMillis());
        }
    }

    private void simulateSlowService() {
        long tiems = 5000L;
        try {
            Thread.sleep(tiems);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
