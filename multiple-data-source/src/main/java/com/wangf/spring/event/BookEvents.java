package com.wangf.spring.event;

import com.wangf.spring.entity.Book;
import com.wangf.spring.utils.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookEvents {

    private final ApplicationEventPublisher eventPublisher;

    public void bookCreated(Book book) {
        eventPublisher.publishEvent(new BookChangedEvent(this, book, Operation.CREATE));
    }

    public void bookUpdated(Book book) {
        eventPublisher.publishEvent(new BookChangedEvent(this, book, Operation.UPDATE));
    }

    public void bookDeleted(Book book) {
        eventPublisher.publishEvent(new BookChangedEvent(this, book, Operation.DELETE));
    }
}
