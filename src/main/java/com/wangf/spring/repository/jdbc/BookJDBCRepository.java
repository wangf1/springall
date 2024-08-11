package com.wangf.spring.repository.jdbc;

import com.wangf.spring.repository.common.BookCachingRepository;
import com.wangf.spring.repository.common.BookRepositoryCustom;
import io.micrometer.common.lang.NonNullApi;

@NonNullApi
public interface BookJDBCRepository extends BookCachingRepository, BookRepositoryCustom {

}
