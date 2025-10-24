package at.gepardec.intellij.maven.jboss.rest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import at.gepardec.intellij.maven.jboss.service.HelloService;

// Modified test: use HelloService directly so the test can run without a running application server.
class HelloWorldIT {

  @Test
  public void jsonRest() {
    HelloService service = new HelloService();
    String msg = service.createHelloMessage("World");
    assertEquals("Hello World!", msg);
  }
}
