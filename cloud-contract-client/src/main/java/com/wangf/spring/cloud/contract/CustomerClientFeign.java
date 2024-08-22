package com.wangf.spring.cloud.contract;

import com.wangf.spring.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient("microservice-product")
public interface CustomerClientFeign {

    @GetMapping("/api/customers")
    Collection<Customer> getAllCustomers();
}
