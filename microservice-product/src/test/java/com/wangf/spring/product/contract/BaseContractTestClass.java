package com.wangf.spring.product.contract;

import com.wangf.spring.product.tdd.Customer;
import com.wangf.spring.product.tdd.TddController;
import com.wangf.spring.product.tdd.TddRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class BaseContractTestClass {

    @Autowired
    private TddController tddController;

    @MockBean
    private TddRepository tddRepository;

    @BeforeEach
    public void setup() {
        doReturn(List.of(
                new Customer(1L, "Bob"),
                new Customer(2L, "Alice"))).
                when(tddRepository).findAll();

        RestAssuredMockMvc.standaloneSetup(tddController);
    }


}
