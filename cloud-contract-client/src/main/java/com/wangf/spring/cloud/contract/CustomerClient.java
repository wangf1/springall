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

    // The URL of the service registered in Eureka
    private final static String URL_MICROSERVICE_PRODUCT = "http://microservice-product";
    private final RestTemplate restTemplate;

    public Collection<Customer> getAllCustomers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Collection<Customer>> responseEntity = restTemplate.exchange(
                URL_MICROSERVICE_PRODUCT + "/api/customers", HttpMethod.GET,
                entity, new ParameterizedTypeReference<>() {
                });
        return responseEntity.getBody();
    }
}
