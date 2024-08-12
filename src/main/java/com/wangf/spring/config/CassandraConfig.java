package com.wangf.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.wangf.spring.repository.cassandra")
public class CassandraConfig {
}
