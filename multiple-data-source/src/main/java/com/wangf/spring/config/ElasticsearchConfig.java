package com.wangf.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.wangf.spring.repository.elasticsearch")
public class ElasticsearchConfig {
}
