package com.wangf.spring.repository.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractBookRepositoryImpl<T> implements BookRepositoryCustom<T> {

    @Override
    public List<T> findAllSlow() {
        return executeWithTiming(() -> {
            simulateSlowService();
            List<T> books = doFindAll();
            return books;
        });
    }

    protected abstract List<T> doFindAll();


    @Override
    public Optional<T> findOneSlow(String isbn) {
        return executeWithTiming(() -> {
            simulateSlowService();
            return doFindByIsbn(isbn);
        });
    }

    protected abstract Optional<T> doFindByIsbn(String isbn);


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
