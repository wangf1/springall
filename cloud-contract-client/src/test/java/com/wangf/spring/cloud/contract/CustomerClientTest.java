package com.wangf.spring.cloud.contract;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "com.wangf.spring:microservice-product:+:8081", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class CustomerClientTest {


    @Autowired
    private CustomerClient customerClient;

    @Test
    void shouldReturnAllCustomers() {
        BDDAssertions.then(this.customerClient.getAllCustomers()).hasSize(2);
        BDDAssertions.then(this.customerClient.getAllCustomers()).extracting("name").contains("Bob", "Alice");
    }
}