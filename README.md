# instamoolah_camunda

## Start
```
./mvnw spring-boot:run -Pcamunda-bpm
```

## API
Complete Tasks
```
curl --header "Content-Type: application/json" --request POST --data '{"variables":{"reason":{"value":"good"}}}' http://localhost:8080/engine-rest/task/<taskId>/complete
```
