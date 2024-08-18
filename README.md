This repository demonstrates how to set up and run various Spring (Boot) features.

## Start

### Prepare .env

Copy src\main\resources\.env.sample as .env in same folder and change property values accordingly.
Copy \.env.sample as .env in same folder and change property values accordingly.

### Run Docker Containers: PostgreSQL, Redis, Cassandra, Elasticsearch, Kafka

```bash
 docker-compose up -d
```

### Start Server

```bash
# In the `config-server` folder, run the following command. Note: Only I can run this successfully
# because I have access to the private Git repository where the configurations are stored.
..\gradlew bootRun

# In multiple-data-source folder, run
..\gradlew bootRun
```

## Tech Stacks

### Docker Compose

For local servers.

### Spring Cache + Redis

For caching.

#### See Spring Work with Redis Cache in Action

**Start additional server with different port in new Terminal:**

```bash
./gradlew bootRun --args='--server.port=8081'
```

**First Request (Expected to take 5 seconds):**

```bash
curl -X GET http://localhost:8080/books/ISBN-1
```

**Second Request (Expected to be very fast due to caching, note that this request goes to a different server instance.
Different server instances share the same cache):**

```bash
curl -X GET http://localhost:8081/books/ISBN-1
```

**Optional:** Download/install RedisInsight to view what has been cached in the Redis container.

### PostgreSQL + Liquibase

Database and schema versioning.

### Cassandra

Can be used instead of SQL DB.

### Elasticsearch

Full text search.

### Spring Cloud Bus - refresh config notification

Refer [SpringDeveloper: Spring Tips: refreshable configuration with Spring Cloud Bus, and the Spring Cloud Config Monitor](https://www.youtube.com/watch?v=aC_siBP8rx8)

- Implement Cloud Config.
- Add "spring-cloud-starter-bus-kafka" dependency for each app.
- Doing Bus Refresh for any application will trigger event indicate each application refresh config.

```bash
 curl -H"content-type: application/json" -d{} http://localhost:8888/actuator/busrefresh
```