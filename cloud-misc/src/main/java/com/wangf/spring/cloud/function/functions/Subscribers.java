package com.wangf.spring.cloud.function.functions;

import com.wangf.spring.cloud.function.entity.Subscriber;
import com.wangf.spring.cloud.function.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class Subscribers {

    private final SubscriberService subscriberService;

    @Bean
    public Supplier<List<Subscriber>> findAll() {
        return subscriberService::findAll;
    }

    @Bean
    public Consumer<String> create() {
        return subscriberService::create;
    }
}
