package com.wangf.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AllSpringFeatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllSpringFeatureApplication.class, args);
    }

}
