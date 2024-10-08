package com.wangf.spring.entity.elasticsearch;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book implements com.wangf.spring.entity.Book {
    @Id
    private String isbn;
    private String title;
}
