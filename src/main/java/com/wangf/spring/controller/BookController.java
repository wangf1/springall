package com.wangf.spring.controller;

import com.wangf.spring.entity.Book;
import com.wangf.spring.service.BookService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{isbn}")
    public Optional<Book> getBookByIsbn(@PathVariable(value = "isbn") String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.updateOrInsertBook(book);
    }

    @PatchMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateOrInsertBook(book);
    }

    @DeleteMapping("/{isbn}")
    public Optional<Book> deleteBook(@PathVariable(value = "isbn") String isbn) {
        return bookService.deleteBook(isbn);
    }
}
