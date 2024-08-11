package com.wangf.spring.repository.jdbc;

import com.wangf.spring.repository.common.BookCachingRepository;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.context.annotation.Profile;

@Profile("jdbc")
@NonNullApi
public interface BookJDBCRepository extends BookCachingRepository {

}
