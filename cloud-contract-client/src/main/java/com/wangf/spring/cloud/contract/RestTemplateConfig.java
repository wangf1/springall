package com.wangf.spring.cloud.contract;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // Make the RestTemplate get service URL from Eureka Server
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
