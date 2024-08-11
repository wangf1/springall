package com.wangf.spring.repository.jpa;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.common.BookCachingRepository;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("jpa")
@NonNullApi
public interface BookJPARepository extends JpaRepository<Book, String>, BookCachingRepository {


}
