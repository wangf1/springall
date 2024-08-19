package com.wangf.spring.cloud.contract;


import com.wangf.spring.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CustomerClient {

    private final RestTemplate restTemplate;

    public Collection<Customer> getAllCustomers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Collection<Customer>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/customers", HttpMethod.GET,
                entity, new ParameterizedTypeReference<>() {
                });
        return responseEntity.getBody();
    }
}
