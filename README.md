# intellij-maven-jboss-workshop

Maven-based Java EE project with REST API, deployable to WildFly 17.

## Requirements

- Java 11
- Maven 3.6+
- WildFly 17.0.1 (downloaded automatically or manually)

## Build

```bash
mvn clean install -DskipTests
```

## Run Integration Tests

```bash
mvn -pl intellij-maven-jboss-helloworld-rs verify -Pit-integration-tests
```

## IntelliJ Run Configurations

| Configuration | Description |
|---------------|-------------|
| WildFly 17 Server | Builds, deploys WAR to WildFly, and starts server |
| Build All (clean install) | Full project build |
| Package WAR | Builds WAR file only |
| Run Integration Tests | Runs HelloWorldIT with integration-tests profile |

### Running with WildFly

1. Open IntelliJ
2. Select "WildFly 17 Server" configuration
3. Click Run - this will:
   - Build the project with Maven
   - Deploy WAR to WildFly
   - Start WildFly server

### Running Integration Tests

1. Start WildFly 17 Server (or manually start WildFly)
2. Run "Run Integration Tests" configuration

## Project Structure

- `intellij-maven-jboss-helloworld-service` - CDI service layer
- `intellij-maven-jboss-helloworld-rs` - JAX-RS REST API (WAR)
- `intellij-maven-jboss-helloworld-persistence` - Persistence layer

## REST Endpoint

```
GET http://localhost:8080/helloworld-rs/hello
```

Returns: `"Hello World!"`
