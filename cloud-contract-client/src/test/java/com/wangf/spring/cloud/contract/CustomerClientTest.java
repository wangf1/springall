package com.wangf.spring.cloud.contract;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;

//TODO Cloud Contract Consumer side test not done yet.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner
class CustomerClientTest {


    @Autowired
    private CustomerClient customerClient;

    @Test
    void shouldReturnAllCustomers() {
        BDDAssertions.then(this.customerClient.getAllCustomers()).hasSize(2);
    }
}