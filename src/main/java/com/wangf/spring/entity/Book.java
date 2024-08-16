package com.wangf.spring.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Table;

@Data
// @Table annotation tell Spring this is entity will store in relational database rather than in Redis
// Without this annotation, Spring will be confused and try to create both JDBC and Redis repositories.
@Table //Indicate this class is a Spring JDBC entity
@Entity // Indicate this class is a Spring JPA entity
@Document(collection = "books") // Indicate this class is a Spring MongoDB entity
// Indicate this class is a Spring Elasticsearch entity
@org.springframework.data.elasticsearch.annotations.Document(indexName = "books")
@org.springframework.data.cassandra.core.mapping.Table // Indicate this class is a Spring Cassandra entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @org.springframework.data.annotation.Id
    @PrimaryKey
    private String isbn;
    private String title;
}
