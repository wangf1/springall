package com.wangf.spring.event;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.common.BookCachingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookEventListener {

    private final CacheManager cacheManager;

    @Async
    @EventListener
    public void handleBookChangedEvent(BookChangedEvent event) {
        updateAllBooksCache(event);
    }

    /**
     * Just play with the cache, don't use it in production. This can lead to consistency issues.
     * It would be quite complex to update collection cache without concurrent issues. Only use Cache eviction
     * can avoid the complexity.
     *
     * @param event BookChangedEvent
     */
    private void updateAllBooksCache(BookChangedEvent event) {
        Cache cache = cacheManager.getCache(BookCachingRepository.BOOKS_CACHE);
        if (cache == null) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) cache.get(BookCachingRepository.ALL_BOOKS, List.class);
        if (books == null) {
            return;
        }

        switch (event.getOperation()) {
            case CREATE:
            case UPDATE:
                int index = -1;
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).getIsbn().equals(event.getBook().getIsbn())) {
                        index = i;
                        break;
                    }
                }

                if (index >= 0) {
                    log.info("Updating book in ALL_BOOKS cache: {}", event.getBook().getIsbn());
                    books.set(index, event.getBook());
                } else {
                    log.info("Adding book to ALL_BOOKS cache: {}", event.getBook().getIsbn());
                    books.add(event.getBook());
                }

                cache.put(BookCachingRepository.ALL_BOOKS, books);
                break;

            case DELETE:
                log.info("Removing book from ALL_BOOKS cache: {}", event.getBook().getIsbn());
                books.removeIf(book -> book.getIsbn().equals(event.getBook().getIsbn()));
                cache.put(BookCachingRepository.ALL_BOOKS, books);
                break;

            default:
                break;
        }
    }
}
