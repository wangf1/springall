package com.wangf.spring.caching;

import java.util.List;

public interface BookRepository {
    Book addBook(Book book);

    Book updateBook(Book book);

    Book deleteBook(String isbn);

    List<Book> getAllBooks();

    Book getByIsbn(String isbn);
}
