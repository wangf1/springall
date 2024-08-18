package com.wangf.spring.service;

import com.wangf.spring.entity.Book;
import com.wangf.spring.event.BookEvents;
import com.wangf.spring.repository.common.BookCachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class AbstractBookService<T extends Book> {

    private final BookCachingRepository<T> bookRepository;

    private final BookEvents bookEvents;


    public Optional<T> getBookByIsbn(String isbn) {
        return bookRepository.findOneSlow(isbn);
    }

    public List<T> getAllBooks() {
        return bookRepository.findAllSlow();
    }

    @Transactional
    public T updateOrInsertBook(T book) {
        Optional<T> byId = bookRepository.findById(book.getIsbn());
        if (byId.isPresent()) {
            bookEvents.bookUpdated(book);
            return bookRepository.save(book);
        } else {
            bookEvents.bookCreated(book);
            bookRepository.insert(book);
        }
        return book;
    }

    @Transactional
    public Optional<T> deleteBook(String isbn) {
        Optional<T> book = bookRepository.findOneSlow(isbn);
        if (book.isEmpty()) {
            return Optional.empty();
        }
        bookEvents.bookDeleted(book.get());
        bookRepository.deleteById(isbn);
        return book;
    }
}
