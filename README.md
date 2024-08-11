This repository demonstrates how to set up and run various Spring (Boot) features.

## Start

### Run Docker Containers: PostgreSQL, Redis

```bash
docker-compose up -d
```

### Start Server

```bash
.\gradlew bootRun
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
