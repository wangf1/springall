package com.wangf.spring.repository.elasticsearch;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.common.AbstractBookRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BookElasticsearchRepositoryImpl extends AbstractBookRepositoryImpl {


    private final ElasticsearchOperations elasticsearchTemplate;

    public BookElasticsearchRepositoryImpl(ElasticsearchOperations elasticsearchRestTemplate, CacheManager cacheManager) {
        super(cacheManager);
        this.elasticsearchTemplate = elasticsearchRestTemplate;
    }

    @Override
    protected List<Book> doFindAll() {
        Query query = new CriteriaQuery(new Criteria());  // Empty criteria to fetch all documents
        SearchHits<Book> searchHits = elasticsearchTemplate.search(query, Book.class);
        Object o = SearchHitSupport.unwrapSearchHits(searchHits);
        return List.of();
    }

    @Override
    protected Optional<Book> doFindByIsbn(String isbn) {
        Criteria criteria = Criteria.where("isbn").is(isbn);
        Query query = new CriteriaQuery(criteria);
        SearchHits<Book> result = elasticsearchTemplate.search(query, Book.class);
        if (result.getSearchHits().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.getSearchHits().get(0).getContent());
    }

    @Override
    public Book insert(Book book) {
        // Insert book into Elasticsearch
        return elasticsearchTemplate.save(book);
    }

}
