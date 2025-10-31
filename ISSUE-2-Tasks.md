# Issue #2 – Task Plan

Checklist
1) Parent POM fixes
   - [x] Set packaging to pom
   - [x] Fix module name typo: `...-persistense` ➜ `...-persistence`
   - [x] Move maven-war-plugin execution out of deploy phase (manage version only)
   - [x] Add pluginManagement versions for surefire, failsafe, compiler

2) Service module
   - [x] Change resources directory to `src/main/resources`
   - [x] Annotate `HelloService` with CDI scope (e.g., `@ApplicationScoped`)
   - [x] Add CDI API as provided to compile the annotation

3) RS module
   - [x] Make CDI and JAX-RS dependencies scope `provided`
   - [x] Fix service dependency (remove classifier; scope compile)
   - [x] Remove non-standard maven-war-plugin configuration; rely on defaults
   - [x] Ensure `beans.xml` under `WEB-INF` has minimal content for CDI discovery

4) Persistence module
   - [x] Ensure it compiles (no-op if empty)

5) Integration tests via failsafe
   - [x] Add maven-failsafe-plugin configured for `*IT.java` under profile `integration-tests`
   - [x] Keep surefire for unit tests only

6) Build and validate
   - [x] Build root with `-DskipTests package` ➜ PASS
   - [x] Verify WAR exists and contains service classes
   - [x] Document IT run steps against WildFly 17

Stretch (optional)
- [ ] Add README snippets for deployment and IT execution
- [ ] Add basic compiler source/target settings if missing
