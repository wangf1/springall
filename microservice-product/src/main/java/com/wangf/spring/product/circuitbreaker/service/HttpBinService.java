package com.wangf.spring.product.circuitbreaker.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.function.Supplier;

@Service
public class HttpBinService {

    private final RestTemplate rest;

    public HttpBinService(RestTemplate rest) {
        this.rest = rest;
    }

    public Map<String, Object> get() {
        return rest.exchange(
                "https://httpbin.org/get",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {
                }
        ).getBody();
    }

    public Map<String, Object> delay(int seconds) {
        return rest.exchange(
                "https://httpbin.org/delay/" + seconds,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {
                }
        ).getBody();
    }

    public Supplier<Map<String, Object>> delaySuppplier(int seconds) {
        return () -> this.delay(seconds);
    }
}