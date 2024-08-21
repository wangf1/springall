Cloud function:

```bash
curl -X POST http://localhost:8083/create -H "Content-Type: text/plain" -d "wang@example.com"
curl -X GET http://localhost:8083/findAll
```