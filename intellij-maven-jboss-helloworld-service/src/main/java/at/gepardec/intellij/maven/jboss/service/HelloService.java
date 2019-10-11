package at.gepardec.intellij.maven.jboss.service;

import javax.enterprise.context.Dependent;

@Dependent
public class HelloService {
    public String createHelloMessage(String name) {
        return "Hello " + name + "!";
    }
}
