# intellij-maven-jboss-workshop

This project is a workshop for fixing a Maven-based Java EE application for WildFly 17.

## Current Project Status
The project is fixed and successfully builds using Maven. The application is deployable on WildFly 17 and includes automated integration tests.

## Prerequisites
- **JDK 11 (Recommended)**, 17, or 21.
- **Maven 3.6.0+**.
- **WildFly 17.0.1.Final**:
  - [Download WildFly 17.0.1.Final](https://download.jboss.org/wildfly/17.0.1.Final/wildfly-17.0.1.Final.zip)
  - Extract it to the project root directory (the folder should be named `wildfly-17.0.1.Final`).

## Java Compatibility & Modern JDK Support (17/21)
While WildFly 17 natively supports Java 11, it has been patched and configured to work with Java 17 and 21.

### Required JVM Flags for Java 17+
If running on Java 17 or 21, the following flags are required for WildFly to start correctly (already configured in `pom.xml` and IntelliJ Run Configuration):
```text
-Djava.security.manager=allow
--add-exports=java.base/sun.nio.ch=ALL-UNNAMED
--add-exports=jdk.unsupported/sun.reflect=ALL-UNNAMED
--add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED
--add-modules=java.se
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens=java.base/java.net=ALL-UNNAMED
--add-opens=java.base/java.security=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent=ALL-UNNAMED
--add-opens=java.management/javax.management=ALL-UNNAMED
--add-opens=java.naming/javax.naming=ALL-UNNAMED
```

## How to Build and Run
### Maven (Command Line)
The `wildfly-maven-plugin` is configured to automatically manage the server lifecycle during the build:
1. Ensure WildFly 17 is extracted to the root directory.
2. Run the full build and integration tests:
   ```bash
   mvn clean install
   ```
   This will:
   - Compile and package all modules.
   - Start the local WildFly instance.
   - Deploy `helloworld-rs.war`.
   - Execute `HelloWorldIT` integration tests.
   - Shut down the server.

### IntelliJ IDEA
A shared Run Configuration is provided in `.idea/runConfigurations/WildFly_17_0_1_Final.xml`.
1. Open the project in IntelliJ.
2. Ensure the "JBoss/WildFly" plugin is enabled.
3. Select the `WildFly 17.0.1.Final` run configuration.
4. Run or Debug it. It is pre-configured to:
   - Perform `mvn clean install` before launch.
   - Run `verify` in the `rs` module (to execute integration tests).
   - Deploy the WAR artifact.

## Key Project Changes
- **CDI Enabled**: Added `@ApplicationScoped` to `HelloService` and included `cdi-api` dependency.
- **Integration Tests**: Configured `maven-failsafe-plugin` for `*IT.java` tests.
- **Automation**: Integrated `wildfly-maven-plugin` for automated server start/stop during build.
- **Red Hat Repo**: Added Red Hat GA repository to `pom.xml` for JBoss-specific dependencies.
- **Modern Java Support**: Patched `bin/standalone.sh` and added modular JVM flags to support Java 17/21.

## Test Verification
You are on a good way if the test `HelloWorldIT` is green.

glhf
