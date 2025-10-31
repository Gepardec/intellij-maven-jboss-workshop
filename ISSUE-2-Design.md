# Issue #2 – Design: Fix the Project for WildFly 17

Overview
We will correct the aggregator POM, fix module wiring, and align module-level POMs and code for WildFly 17 compatibility. The goal is a clean `package` build and a deployable WAR with working CDI and JAX-RS endpoints.

Key design decisions
- Root POM as aggregator-only: packaging=pom; manage shared plugin versions, not executions.
- Correct module name from `persistense` to `persistence` to include the module in builds.
- Java EE APIs marked as `provided` in WAR module; rely on container.
- Service module produces a plain JAR included in WAR; add CDI bean-defining annotation to `HelloService`.
- Use maven-failsafe-plugin for integration tests; keep surefire for unit tests only. ITs should not block `package` phase when server is not running.

Module details
- Root (parent/aggregator)
  - packaging: pom
  - properties: centralize versions: commons-collections4, commons-lang3, junit5, rest-assured
  - pluginManagement: declare versions only (maven-war-plugin, maven-compiler-plugin, surefire/failsafe)

- intellij-maven-jboss-helloworld-service (JAR)
  - Ensure resources path uses `src/main/resources` and includes `META-INF/beans.xml` if present.
  - Mark `HelloService` as CDI bean with `@ApplicationScoped`.

- intellij-maven-jboss-helloworld-rs (WAR)
  - Dependencies
    - Add CDI API and JAX-RS API as `provided` (on WildFly 17: CDI 2.0, JAX-RS 2.1).
    - Change service dependency to normal compile, without classifier, so it gets packaged.
    - Scope libraries that are container-provided to `provided`.
  - Packaging
    - Use default webapp dir `src/main/webapp`; remove custom `warSourceDirectory` and `webappDirectory`.
    - Ensure `beans.xml` in `WEB-INF` (already present) and a JAX-RS `Application` subclass (present: `JAXActivator`).

- intellij-maven-jboss-helloworld-persistence (JAR)
  - Ensure the module compiles; not used by WAR currently, but included to keep reactor green.

Testing approach
- Unit tests via surefire (default).
- Integration tests via failsafe bound to `integration-test` and `verify`, including patterns `*IT.java`. Provide a profile to enable ITs.

Endpoints and behavior
- JAX-RS base path `/rest` configured by `JAXActivator`.
- HelloWorld resource at `GET /rest/json` returns `{ "result": "Hello World!" }`.
- Injection of `HelloService` used to build the message.

Build profiles
- Default: unit tests only; ITs skipped.
- Profile `integration-tests`: enable failsafe and execute `*IT.java`.

Deployment notes
- Context root is war file name: `helloworld-rs`. Final URL tested by IT: `/helloworld-rs/rest/json`.
