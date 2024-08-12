package com.wangf.spring.repository.cassandra;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.repository.CassandraRepository;

@Profile("cassandra")
public interface BookCassandraRepository extends CassandraRepository<Book, String>, BookCachingRepository {
}
