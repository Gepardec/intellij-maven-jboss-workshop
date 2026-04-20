package at.gepardec.intellij.maven.jboss.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {
    public String createHelloMessage(String name) {
        return "Hello " + name + "!";
    }
}
