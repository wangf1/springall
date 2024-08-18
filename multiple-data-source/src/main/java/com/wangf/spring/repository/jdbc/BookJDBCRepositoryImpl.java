package com.wangf.spring.repository.jdbc;

import com.wangf.spring.entity.jdbc.Book;
import com.wangf.spring.repository.common.AbstractBookRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BookJDBCRepositoryImpl extends AbstractBookRepositoryImpl<Book> {

    private static final String BOOKS_CACHE = BookJDBCRepository.BOOKS_CACHE;
    private final JdbcTemplate jdbcTemplate;


    public BookJDBCRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected List<Book> doFindAll() {
        return jdbcTemplate.query("select * from book", (rs, rowNum) -> {
            String isbn = rs.getString("isbn");
            String title = rs.getString("title");
            return new Book(isbn, title);
        });
    }

    @Override
    protected Optional<Book> doFindByIsbn(String isbn) {
        List<Book> result = jdbcTemplate.query("select * from book where isbn = ?",
                (rs, rowNum) -> {
                    String title = rs.getString("title");
                    return new Book(isbn, title);
                }, isbn);
        if (!result.isEmpty()) {
            return Optional.of(result.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Book insert(Book book) {
        jdbcTemplate.update("insert into book (isbn, title) values (?, ?)", book.getIsbn(), book.getTitle());
        return book;
    }

}
