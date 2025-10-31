# intellij-maven-jboss-workshop

Try to fix the maven project!

you are only allowed to use intellij. no commandline. no git-bash. etc.

first create a feature branch with your name like. feature/hwirnsberger

you should be able to deploy the war archive in intellij to wildfly.
https://download.jboss.org/wildfly/17.0.1.Final/wildfly-17.0.1.Final.zip

you are on a good way if the test HelloWorldIT is green.

if you are satisfied with your solution push your branch

glhf

---

Build, deploy and test (WildFly 17)
- Build the project (tests skipped):
```bash
mvn -DskipTests package
```
- Deploy the WAR `intellij-maven-jboss-helloworld-rs/target/helloworld-rs.war` to a running WildFly 17 instance (context root: `helloworld-rs`).
- Verify manually:
```bash
curl -s http://localhost:8080/helloworld-rs/rest/json
# => {"result":"Hello World!"}
```
- Run integration test when the server is running:
```bash
mvn -pl intellij-maven-jboss-helloworld-rs -Pintegration-tests verify
```
Notes
- Java EE APIs are provided by the container; the WAR includes the service JAR.
- The REST base path is `/rest` (see `JAXActivator`).

Run WildFly 17 (options)
- Docker (recommended):
```bash
./scripts/run-wildfly-docker.sh
```
- Manual (no Docker):
  - Download: https://download.jboss.org/wildfly/17.0.1.Final/wildfly-17.0.1.Final.zip
  - Unzip and start: bin/standalone.sh -b 0.0.0.0
  - Deploy the WAR via the admin console (port 9990) or copy the WAR to standalone/deployments
