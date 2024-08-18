package com.wangf.spring.controller;

import com.wangf.spring.dto.BookDto;
import com.wangf.spring.entity.jpa.Book;
import com.wangf.spring.mapper.DtoToEntity;
import com.wangf.spring.service.AbstractBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
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
@Profile("jpa")
@RequiredArgsConstructor
public class JpaBookController {

    private final AbstractBookService<Book> BookService;

    private final DtoToEntity dtoToEntity;


    @GetMapping("/{isbn}")
    public Optional<Book> getBookDtoByIsbn(@PathVariable(value = "isbn") String isbn) {
        return BookService.getBookByIsbn(isbn);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return BookService.getAllBooks();
    }

    @PostMapping
    public com.wangf.spring.entity.Book addBook(@RequestBody BookDto bookDto) {
        Book book = (Book) dtoToEntity.dtoToEntity(bookDto);
        return BookService.updateOrInsertBook(book);
    }

    @PatchMapping
    public Book updateBook(@RequestBody BookDto bookDto) {
        Book book = (Book) dtoToEntity.dtoToEntity(bookDto);
        return BookService.updateOrInsertBook(book);
    }

    @DeleteMapping("/{isbn}")
    public Optional<Book> deleteBook(@PathVariable(value = "isbn") String isbn) {
        return BookService.deleteBook(isbn);
    }
}
