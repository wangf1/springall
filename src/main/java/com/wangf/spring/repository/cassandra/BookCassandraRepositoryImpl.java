package com.wangf.spring.repository.cassandra;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.common.AbstractBookRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BookCassandraRepositoryImpl extends AbstractBookRepositoryImpl {

    private final CassandraTemplate cassandraTemplate;

    public BookCassandraRepositoryImpl(CassandraTemplate cassandraTemplate, CacheManager cacheManager) {
        super(cacheManager);
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    protected List<Book> doFindAll() {
        Query query = Query.empty(); // Equivalent to fetching all records
        return cassandraTemplate.select(query, Book.class);
    }

    @Override
    protected Optional<Book> doFindByIsbn(String isbn) {
        Query query = Query.query(Criteria.where("isbn").is(isbn));
        Book book = cassandraTemplate.selectOne(query, Book.class);
        return Optional.ofNullable(book);
    }

    @Override
    public Book insert(Book book) {
        cassandraTemplate.insert(book);
        return book;
    }

}
