package com.wangf.spring.cloud.contract;


import com.wangf.spring.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CustomerClient {

    private final RestTemplate restTemplate;

    public Collection<Customer> getAllCustomers() {
        ResponseEntity<Collection<Customer>> responseEntity = restTemplate.exchange(
                "http://localhost:8080/customers", HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });
        return responseEntity.getBody();
    }
}
