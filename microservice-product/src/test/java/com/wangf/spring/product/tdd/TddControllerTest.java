package com.wangf.spring.product.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TddControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TddRepository tddRepository;

    @Test
    void shouldReturnAllCus() throws Exception {

        doReturn(List.of(
                new Customer(1L, "Bob"),
                new Customer(2L, "Alice"))).
                when(tddRepository).findAll();

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("@.[0].id").value(1L))
                .andExpect(jsonPath("@.[0].name").value("Bob"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
