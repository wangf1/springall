package com.wangf.spring.caching;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
// @Table annotation tell Spring this is entity will store in relational database rather than in Redis
// Without this annotation, Spring will be confused and try to create both JDBC and Redis repositories.
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String isbn;
    private String title;
}
