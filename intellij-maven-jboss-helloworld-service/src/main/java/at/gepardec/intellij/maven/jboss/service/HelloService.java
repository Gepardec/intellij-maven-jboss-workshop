package at.gepardec.intellij.maven.jboss.service;

import javax.ejb.Stateless;

@Stateless
public class HelloService {
    public String createHelloMessage(String name) {
        return "Hello " + name + "!";
    }
}
