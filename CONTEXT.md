# Rolling Context – Issue #2

Date: 2025-10-31

Goal
- Fix Maven build, produce WAR deployable on WildFly 17, validated by HelloWorldIT.

Initial observations
- Root POM lacked `<packaging>pom</packaging>` and had typo in modules: `...-persistense`.
- Root managed maven-war-plugin with an odd execution in deploy phase.
- RS module had wrong scopes and custom war plugin dirs; service dependency used classifier/provided.
- `beans.xml` in RS was empty; service module used `src/main/webresources`; `HelloService` not annotated.

Changes applied
- Parent POM: set packaging=pom, fixed module name to `...-persistence`, simplified pluginManagement (war/surefire/failsafe versions).
- Service module: fixed resources dir to `src/main/resources`; added CDI API (provided); removed jar classifier; annotated `HelloService` with `@ApplicationScoped`.
- RS module: marked CDI/JAX-RS/annotations specs as provided with central-available versions; removed classifier from service dependency; simplified war plugin; added surefire config for unit tests; moved failsafe into optional `integration-tests` profile.
- beans.xml: added minimal CDI 2.0 descriptor with bean-discovery-mode=annotated.
- README: added Build/Deploy/IT instructions for WildFly 17.

Current status
- `mvn -DskipTests package` succeeds.
- Generated WAR `intellij-maven-jboss-helloworld-rs/target/helloworld-rs.war` contains:
  - REST classes under `WEB-INF/classes`
  - Service jar under `WEB-INF/lib`
  - beans.xml and web.xml present

Next steps
- Deploy WAR to WildFly 17 and run ITs (`-Pintegration-tests`).
- If needed, add README with run instructions.
