package com.wangf.spring.product.observability.post;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@Configuration
@Slf4j
public class PostsConfig {


    @Bean
    public JsonPlaceholderService postService() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(restClient)).build();

        return factory.createClient(JsonPlaceholderService.class);
    }

    @Bean
    public CommandLineRunner run(JsonPlaceholderService jsonPlaceholderService,
                                 ObservationRegistry observationRegistry) {
        return args -> Observation.createNotStarted("posts.load-all-posts", observationRegistry).
                lowCardinalityKeyValue("service", "jsonplaceholder").
                contextualName("posts-service.find-all").
                observe(() -> loadAllPostsAndDoSomethingWhitIt(jsonPlaceholderService));
    }

    private void loadAllPostsAndDoSomethingWhitIt(JsonPlaceholderService jsonPlaceholderService) {
        List<Post> all = jsonPlaceholderService.findAll();
        log.info("All posts: {}", all.size());
    }

    @Bean
    @Observed(name = "posts.load-all-posts2", contextualName = "posts-service.find-all2")
    public CommandLineRunner run2(JsonPlaceholderService jsonPlaceholderService) {
        return args -> {
            log.info("The second command line runner...");
            loadAllPostsAndDoSomethingWhitIt(jsonPlaceholderService);
        };
    }
}
