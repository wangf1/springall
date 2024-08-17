package com.wangf.spring.entity.cassandra;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Book implements com.wangf.spring.entity.Book {
    @PrimaryKey
    private String isbn;
    private String title;
}
