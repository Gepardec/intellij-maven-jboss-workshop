# Issue #2 – Specification: Fix the Project

Issue link: https://github.com/Gepardec/intellij-maven-jboss-workshop/issues/2

Problem statement
- Maven build is broken in this multi-module project.
- The built WAR must be deployable on WildFly 17 (Java EE 8 / JBoss EAP 7.2 era APIs).
- Validate the application by testing it with HelloWorldIT (REST endpoint expected to return JSON with result "Hello World!").

In-scope
- Make the Maven build succeed from the project root, producing a deployable WAR for the module `intellij-maven-jboss-helloworld-rs`.
- Ensure CDI injection works for `HelloService` when deployed on WildFly 17.
- Ensure REST endpoint `/helloworld-rs/rest/json` returns the expected payload.
- Align dependency scopes with WildFly 17 (Java EE APIs as provided).
- Align packaging and plugin configuration to sane defaults (war created in package phase).
- Configure IT execution appropriately so a normal build is green; ITs can be run when a WildFly instance is available.

Out of scope
- Adding new business features or endpoints beyond HelloWorld.
- Changing container version (fixed to WildFly 17 as per issue).
- Adding CI/CD pipelines (optional follow-up).

Success criteria / acceptance
- `mvn -q -DskipTests package` from repository root finishes successfully and produces `intellij-maven-jboss-helloworld-rs/target/helloworld-rs.war`.
- The WAR deploys on WildFly 17 without missing classes or dependency conflicts.
- With WildFly 17 running and the WAR deployed under context `helloworld-rs`, `HelloWorldIT` passes (HTTP 200; JSON body `{ "result": "Hello World!" }`).

Constraints and environment
- Java 8/11 compatible (WildFly 17 supports both; prefer Java 11 if available, otherwise Java 8).
- Rely on container-provided Java EE 8 APIs (CDI 2.0, JAX-RS 2.1) with scope `provided`.
- Keep modules and coordinates stable; no group/artifact/version changes beyond what is necessary.

High-level requirements to fix
1) Aggregator POM corrections
   - Set `<packaging>pom</packaging>` for the root POM.
   - Fix the misspelled module entry `intellij-maven-jboss-helloworld-persistense` ➜ `intellij-maven-jboss-helloworld-persistence`.
   - Clean up pluginManagement for maven-war-plugin (remove unusual execution in deploy phase; manage version only).

2) Service module packaging and CDI
   - Ensure `src/main/resources` is included (beans.xml packaged correctly).
   - Add bean-defining annotation to `HelloService` (e.g., `@ApplicationScoped` or `@Dependent`).

3) RS (WAR) module packaging and dependencies
   - Remove/adjust custom maven-war-plugin configuration to use default `src/main/webapp`.
   - Ensure Java EE APIs (CDI, JAX-RS) are `provided`.
   - Ensure the service JAR is a normal compile dependency (no classifier; not `provided`) so it's packaged into the WAR.

4) Integration tests strategy
   - Move `*IT.java` execution to maven-failsafe-plugin bound to `verify` (so default `package` does not require a running server).
   - Keep `HelloWorldIT` unchanged (as requested), but provide run instructions for IT when WildFly 17 is running.

Validation plan
- Local build: `mvn -DskipTests package` at repo root ➜ PASS.
- Smoke: Start WildFly 17, deploy generated WAR, curl GET `/helloworld-rs/rest/json` ➜ `{"result":"Hello World!"}`.
- IT: Run `mvn -pl intellij-maven-jboss-helloworld-rs -am -DskipUTs -Pintegration-tests verify` (or simply `mvn verify` if profile not used) with server running ➜ PASS.

Risks
- CDI bean discovery depends on beans.xml presence and bean-defining annotations; addressed via both measures.
- Version alignment of Java EE specs; stick to established versions and `provided` scope to avoid conflicts with container.
