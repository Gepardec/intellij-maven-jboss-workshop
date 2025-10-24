package at.gepardec.intellij.maven.jboss.rest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import at.gepardec.intellij.maven.jboss.service.HelloService;

class HelloWorldTest {

  @Test
  public void createMessage() {
    HelloService service = new HelloService();
    String msg = service.createHelloMessage("World");
    assertEquals("Hello World!", msg);
  }
}

