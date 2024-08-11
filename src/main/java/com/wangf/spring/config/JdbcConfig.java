package com.wangf.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@Configuration
@EnableJdbcRepositories(
        basePackages = "com.wangf.spring.repository.jdbc"
)
public class JdbcConfig {
}
