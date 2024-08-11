package com.wangf.spring.repository.jdbc;

import com.wangf.spring.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryCustom {

    List<Book> findAllSlow();

    Optional<Book> findOneSlow(String isbn);


}
