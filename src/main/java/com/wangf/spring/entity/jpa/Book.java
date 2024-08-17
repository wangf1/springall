package com.wangf.spring.entity.jpa;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book implements com.wangf.spring.entity.Book {
    @Id
    private String isbn;
    private String title;
}
