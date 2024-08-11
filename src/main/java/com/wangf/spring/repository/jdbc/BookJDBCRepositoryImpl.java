package com.wangf.spring.repository.jdbc;

import com.wangf.spring.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
public class BookJDBCRepositoryImpl implements BookRepositoryCustom {

    private static final String BOOKS_CACHE = BookJDBCRepository.BOOKS_CACHE;
    private final CacheManager cacheManager;
    private final JdbcTemplate jdbcTemplate;


    @Override
    @Cacheable(value = BOOKS_CACHE)
    public List<Book> findAllSlow() {
        return executeWithTiming(() -> {
            simulateSlowService();
            List<Book> books = jdbcTemplate.query("select * from book", (rs, _) -> {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                return new Book(isbn, title);
            });
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
    public Optional<Book> findOneSlow(String isbn) {
        return executeWithTiming(() -> {
            simulateSlowService();
            List<Book> result = jdbcTemplate.query("select * from book where isbn = ?",
                    (rs, _) -> {
                        String title = rs.getString("title");
                        return new Book(isbn, title);
                    }, isbn);
            if (!result.isEmpty()) {
                return Optional.of(result.getFirst());
            }
            return Optional.empty();
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
