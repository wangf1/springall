package com.wangf.spring.product.observability.post;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface JsonPlaceholderService {
    @GetExchange("/posts")
    List<Post> findAll();

    @GetExchange("/posts/{id}")
    Post findById(@PathVariable("id") Integer id);
}
