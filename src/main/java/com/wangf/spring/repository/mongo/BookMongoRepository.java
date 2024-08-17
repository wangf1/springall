package com.wangf.spring.repository.mongo;

import com.wangf.spring.entity.mongo.Book;
import com.wangf.spring.repository.common.BookCachingRepository;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

@Profile("mongo")
@NonNullApi
public interface BookMongoRepository extends MongoRepository<Book, String>, BookCachingRepository<Book> {


}
