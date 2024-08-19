package com.wangf.spring.product.tdd;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class TddController {

    private final TddService tddService;

    @GetMapping
    public Collection<Customer> all() {
        return tddService.getAllCustomers();
    }
}
