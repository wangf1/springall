package com.wangf.spring.product.tdd;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TddService {

    private final TddRepository tddRepository;


    public Collection<Customer> getAllCustomers() {
        return tddRepository.findAll();
    }
}
