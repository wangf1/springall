package com.wangf.spring.product.observability.post;

public record Post(Integer id, Integer userId,
                   String title, String body) {
}
