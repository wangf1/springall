package com.wangf.spring.caching;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable(value = "isbn") String isbn) {
        return bookRepository.getByIsbn(isbn);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.addBook(book);
    }

    @PatchMapping
    public Book updateBook(@RequestBody Book book) {
        return bookRepository.updateBook(book);
    }

    @DeleteMapping("/{isbn}")
    public Book deleteBook(@PathVariable(value = "isbn") String isbn) {
        return bookRepository.deleteBook(isbn);
    }
}
