package com.wangf.spring.service;

import com.wangf.spring.entity.Book;
import com.wangf.spring.event.BookEvents;
import com.wangf.spring.repository.common.BookCachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookCachingRepository bookRepository;

    private final BookEvents bookEvents;


    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findOneSlow(isbn);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllSlow();
    }

    @Transactional
    public Book updateOrInsertBook(Book book) {
        Optional<Book> byId = bookRepository.findById(book.getIsbn());
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
    public Optional<Book> deleteBook(String isbn) {
        Optional<Book> book = bookRepository.findOneSlow(isbn);
        if (book.isEmpty()) {
            return Optional.empty();
        }
        bookEvents.bookDeleted(book.get());
        bookRepository.deleteById(isbn);
        return book;
    }
}
