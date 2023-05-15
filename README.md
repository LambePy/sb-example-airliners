# Airliner API - Spring boot 3 Example 
This is a rough example of Spring Boot 3 REST-API

Goal of this project is to provide good structure for your spring boot 3 RESTful services and also for me to do something when I'm bored.

Pull requests and suggestions are welcome!

This Airliner management demo uses H2 in-memory database in PostgreSQL mode.
Operations are made with REST API


Currently only local mode supported

## How to run this app

Required: JDK 17 and Maven 3.6.x (Developed with 3.8.1)
Recommended: EclipseEE IDE or Intellij IDEA
Building and running recommendation: MING64 - GitBash / other command lines

## Starting the app

- Clone the app with 

```bash 
$ Git clone git@github.com:LambePy/sb-example-airliners.git
```

- Build the project

```bash 
$ mvn clean install
```

- Start the app 

```bash 
$ mvn spring-boot:run || mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Architecture

This is a down-top model where API specificaiton is created with OpenAPI 3 annotations.
Other approach is top-down where API specification is generated from OpenApi.yaml (will make this in future)

OpenAPI definition can be found from
[Swagger UI](http://localhost:8080/swagger-ui/index.html)
[API-DOCS](http://localhost:8080/v3/api-docs)

### Logging

Logging is done with SLF4J Logback
Every API-call is logged with structured logstash format




# DB
```
DROP TABLE IF EXISTS airliner;
CREATE TABLE airliner (
  airliner_id VARCHAR(255) NOT NULL,
  airliner_code VARCHAR(10) NOT NULL,
  country VARCHAR(50) NOT NULL,
  created TIMESTAMP(6) NOT NULL,
  airliner_name VARCHAR(50) NOT NULL,
  active VARCHAR(10) NOT NULL,
  updated TIMESTAMP(6),
  PRIMARY KEY (airliner_id)
);
```
```
DROP TABLE IF EXISTS aircraft;
CREATE TABLE aircraft (
  aircraft_id VARCHAR(255) NOT NULL,
  created TIMESTAMP(6) NOT NULL,
  manufacturer VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  updated TIMESTAMP(6),
  airliner_id_fk VARCHAR(255),
  PRIMARY KEY (aircraft_id),
  FOREIGN KEY (airliner_id_fk) REFERENCES airliner (airliner_id)
);
```

