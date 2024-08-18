package com.wangf.spring.product.circuitbreaker.controller;

import com.wangf.spring.product.circuitbreaker.service.HttpBinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class CircuitBreakerDemoController {

    private final CircuitBreakerFactory<Resilience4JCircuitBreakerConfiguration, Resilience4JConfigBuilder> circuitBreakerFactory;
    private final HttpBinService httpBin;

    public CircuitBreakerDemoController(CircuitBreakerFactory<Resilience4JCircuitBreakerConfiguration, Resilience4JConfigBuilder> circuitBreakerFactory, HttpBinService httpBinService) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.httpBin = httpBinService;
    }

    @GetMapping("/get")
    public Map<String, Object> get() {
        return httpBin.get();
    }

    @GetMapping("/delay/{seconds}")
    public Map<String, Object> delay(@PathVariable int seconds) {
        return circuitBreakerFactory.create("delay").run(httpBin.delaySuppplier(seconds), t -> {
            log.warn("delay call failed error", t);
            Map<String, Object> fallback = new HashMap<>();
            fallback.put("hello", "world");
            return fallback;
        });
    }
}
