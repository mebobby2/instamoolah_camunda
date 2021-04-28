# instamoolah_camunda

## Prerequisites
* PostgreSQL (postgres.app)
* Kafka

```
docker-compose up -d
```
## Start
Build dependencies first (from root/parent project directory)
```
./mvnw clean install -Pcamunda-bpm
```

Run Application (from loans project directory)
```
./mvnw spring-boot:run -Pcamunda-bpm
```

## API
Complete Tasks
```
curl --header "Content-Type: application/json" --request POST --data '{"variables":{"reason":{"value":"good"}}}' http://localhost:8080/engine-rest/task/<taskId>/complete
```
