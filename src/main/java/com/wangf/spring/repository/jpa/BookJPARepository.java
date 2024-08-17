package com.wangf.spring.repository.jpa;

import com.wangf.spring.entity.jpa.Book;
import com.wangf.spring.repository.common.BookCachingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("jpa")
public interface BookJPARepository extends JpaRepository<Book, String>, BookCachingRepository<Book> {


}
