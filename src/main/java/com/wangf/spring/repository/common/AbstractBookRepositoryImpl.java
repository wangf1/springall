package com.wangf.spring.repository.common;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.jdbc.BookJDBCRepository;
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
public abstract class AbstractBookRepositoryImpl implements BookRepositoryCustom {

    private static final String BOOKS_CACHE = BookJDBCRepository.BOOKS_CACHE;
    protected final CacheManager cacheManager;


    @Override
    public List<Book> findAllSlow() {
        return executeWithTiming(() -> {
            simulateSlowService();
            List<Book> books = doFindAll();
            putEveryBookIntoCache(books);
            return books;
        });
    }

    protected abstract List<Book> doFindAll();

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
            return doFindByIsbn(isbn);
        });
    }

    protected abstract Optional<Book> doFindByIsbn(String isbn);


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
