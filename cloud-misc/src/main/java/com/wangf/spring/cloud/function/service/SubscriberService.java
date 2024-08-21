package com.wangf.spring.cloud.function.service;


import com.wangf.spring.cloud.function.entity.Subscriber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SubscriberService {
    private final List<Subscriber> subscribers = new ArrayList<>();

    private final AtomicInteger id = new AtomicInteger(0);

    public List<Subscriber> findAll() {
        return subscribers;
    }

    public Subscriber create(String email) {
        Subscriber subscriber = new Subscriber(id.incrementAndGet(), email);
        subscribers.add(subscriber);
        return subscriber;
    }
}
