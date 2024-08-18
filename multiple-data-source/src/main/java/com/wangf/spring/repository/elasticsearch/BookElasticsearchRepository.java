package com.wangf.spring.repository.elasticsearch;

import com.wangf.spring.entity.elasticsearch.Book;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Profile("elasticsearch")
public interface BookElasticsearchRepository extends ElasticsearchRepository<Book, String>, BookCachingRepository<Book> {

}
