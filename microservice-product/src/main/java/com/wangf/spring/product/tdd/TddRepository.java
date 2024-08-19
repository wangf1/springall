package com.wangf.spring.product.tdd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TddRepository extends JpaRepository<Customer, Long> {
}
