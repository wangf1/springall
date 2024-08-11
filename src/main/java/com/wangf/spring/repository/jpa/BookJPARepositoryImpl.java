package com.wangf.spring.repository.jpa;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.common.BookRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
public class BookJPARepositoryImpl implements BookRepositoryCustom {

    private static final String BOOKS_CACHE = BookJPARepository.BOOKS_CACHE;
    private final CacheManager cacheManager;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAllSlow() {
        return executeWithTiming(() -> {
            simulateSlowService();
            List<Book> books = entityManager.createQuery("SELECT b FROM Book b", Book.class)
                    .getResultList();
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
            List<Book> result = entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                    .setParameter("isbn", isbn)
                    .getResultList();
            return result.stream().findFirst();
        });
    }

    @Override
    public Book insert(Book book) {
        entityManager.persist(book);
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
