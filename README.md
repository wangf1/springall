This repository demonstrates how to set up and run various Spring (Boot) features.

## See Spring Work with Redis Cache in Action

### Run Redis Container

```bash
docker pull redis
docker run --name redis-container -d -p 6379:6379 redis
```

### Start Two Server Instances

**Terminal 1:**

```bash
.\gradlew bootRun
```

**Terminal 2:**

```bash
./gradlew bootRun --args='--server.port=8081'
```

### Test the Redis Cache

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

Cache-related code is in
the [cache package](https://github.com/wangf1/springall/tree/main/src/main/java/com/wangf/spring/caching).

