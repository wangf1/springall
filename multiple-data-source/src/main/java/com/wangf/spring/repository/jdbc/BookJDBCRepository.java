package com.wangf.spring.repository.jdbc;

import com.wangf.spring.entity.jdbc.Book;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;

@Profile("jdbc")
public interface BookJDBCRepository extends BookCachingRepository<Book> {

}
