package com.wangf.spring.repository.jpa;

import com.wangf.spring.entity.jpa.Book;
import com.wangf.spring.repository.common.AbstractBookRepositoryImpl;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BookJPARepositoryImpl extends AbstractBookRepositoryImpl<Book> {


    private final EntityManager entityManager;


    public BookJPARepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    protected List<Book> doFindAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }


    @Override
    protected Optional<Book> doFindByIsbn(String isbn) {
        List<Book> result = entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getResultList();
        return result.stream().findFirst();
    }

    @Override
    public Book insert(Book book) {
        entityManager.persist(book);
        return book;
    }


}
