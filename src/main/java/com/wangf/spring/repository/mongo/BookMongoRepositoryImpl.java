package com.wangf.spring.repository.mongo;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.common.BookRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
public class BookMongoRepositoryImpl implements BookRepositoryCustom {

    private static final String BOOKS_CACHE = BookMongoRepository.BOOKS_CACHE;
    private final CacheManager cacheManager;
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Book> findAllSlow() {
        return executeWithTiming(() -> {
            simulateSlowService();
            Query query = new Query(); // Create a query for all documents
            List<Book> books = mongoTemplate.find(query, Book.class);
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
    public Optional<Book> findOneSlow(String isbn) {
        return executeWithTiming(() -> {
            simulateSlowService();
            Query query = new Query(Criteria.where("isbn").is(isbn)); // Query for specific ISBN
            Book book = mongoTemplate.findOne(query, Book.class);
            return Optional.ofNullable(book);
        });
    }

    @Override
    public Book insert(Book book) {
        mongoTemplate.save(book); // Use save method to insert or update
        return book;
    }

    private <T> T executeWithTiming(Supplier<T> supplier) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return supplier.get();
        } finally {
            stopWatch.stop();
            log.info("Time elapsed: {} ms", stopWatch.getTotalTimeMillis());
        }
    }

    private void simulateSlowService() {
        long delay = 5000L;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}
