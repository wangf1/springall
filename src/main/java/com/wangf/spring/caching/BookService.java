package com.wangf.spring.caching;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findOneSlow(isbn);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllSlow();
    }

    @Transactional
    @CachePut(value = BookRepository.BOOKS_CACHE, key = "#book.isbn")
    public Book updateOrInsertBook(Book book) {
        Optional<Book> byId = bookRepository.findById(book.getIsbn());
        if (byId.isPresent()) {
            return bookRepository.save(book);
        } else {
            bookRepository.insert(book);
        }
        return book;
    }

    @Transactional
    public Optional<Book> deleteBook(String isbn) {
        Optional<Book> book = bookRepository.findOneSlow(isbn);
        bookRepository.deleteById(isbn);
        return book;
    }
}
