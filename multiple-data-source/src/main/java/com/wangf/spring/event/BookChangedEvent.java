package com.wangf.spring.event;

import com.wangf.spring.entity.Book;
import com.wangf.spring.utils.Operation;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class BookChangedEvent extends ApplicationEvent {
    @Getter
    private final Book book;
    @Getter
    private final Operation operation;

    public BookChangedEvent(Object source, Book book, Operation operation) {
        super(source);
        this.book = book;
        this.operation = operation;
    }

}