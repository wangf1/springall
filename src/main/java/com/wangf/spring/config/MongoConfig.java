package com.wangf.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.wangf.spring.repository.mongo"
)
public class MongoConfig {
}
