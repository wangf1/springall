package com.wangf.spring.caching;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String isbn;
    private String title;
}
