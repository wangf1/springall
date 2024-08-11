package com.wangf.spring.repository.jpa;

import com.wangf.spring.entity.Book;
import com.wangf.spring.repository.common.BookCachingRepository;
import com.wangf.spring.repository.common.BookRepositoryCustom;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;

@NonNullApi
public interface BookJPARepository extends JpaRepository<Book, String>, BookCachingRepository, BookRepositoryCustom {


}
