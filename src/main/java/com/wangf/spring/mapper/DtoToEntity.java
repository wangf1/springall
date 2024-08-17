package com.wangf.spring.mapper;


import com.wangf.spring.dto.BookDto;
import com.wangf.spring.entity.Book;
import com.wangf.spring.utils.Profiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class DtoToEntity {
    @Autowired
    private Environment environment;

    public Book dtoToEntity(BookDto bookDto) {
        Set<String> activeProfiles = Arrays.stream(environment.getActiveProfiles()).collect(toSet());
        if (activeProfiles.contains(Profiles.JDBC)) {
            return new com.wangf.spring.entity.jdbc.Book(bookDto.getIsbn(), bookDto.getTitle());
        } else if (activeProfiles.contains(Profiles.MONGO)) {
            return new com.wangf.spring.entity.mongo.Book(bookDto.getIsbn(), bookDto.getTitle());
        } else if (activeProfiles.contains(Profiles.CASSANDRA)) {
            return new com.wangf.spring.entity.cassandra.Book(bookDto.getIsbn(), bookDto.getTitle());
        } else if (activeProfiles.contains(Profiles.ELASTICSEARCH)) {
            return new com.wangf.spring.entity.elasticsearch.Book(bookDto.getIsbn(), bookDto.getTitle());
        } else if (activeProfiles.contains(Profiles.JPA)) {
            return new com.wangf.spring.entity.jpa.Book(bookDto.getIsbn(), bookDto.getTitle());
        } else {
            return new com.wangf.spring.entity.jdbc.Book(bookDto.getIsbn(), bookDto.getTitle());
        }
    }
}