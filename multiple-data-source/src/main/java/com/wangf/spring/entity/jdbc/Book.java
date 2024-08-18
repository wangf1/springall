package com.wangf.spring.entity.jdbc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Book implements com.wangf.spring.entity.Book {
    @Id
    private String isbn;
    private String title;
}
