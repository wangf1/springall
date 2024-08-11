package com.wangf.spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.wangf.spring.repository.jpa"
)
@EntityScan(basePackages = "com.wangf.spring.entity")
public class JpaConfig {
}
