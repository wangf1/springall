package com.wangf.spring.cloud.contract;

import com.wangf.spring.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/client/customers")
@RequiredArgsConstructor
public class CustomerClientController {

    private final CustomerClient customerClient;

    private final CustomerClientFeign customerClientFeign;

    @RequestMapping
    public Collection<Customer> customers() {
        return customerClient.getAllCustomers();
    }

    // Use http://localhost:8082/api/client/customers/feign to access
    @RequestMapping("feign")
    public Collection<Customer> customers2() {
        return customerClientFeign.getAllCustomers();
    }
}
