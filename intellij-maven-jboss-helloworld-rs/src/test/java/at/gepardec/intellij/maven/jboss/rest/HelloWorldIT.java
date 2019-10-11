package at.gepardec.intellij.maven.jboss.rest;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//DO NOT EDIT!!!
class HelloWorldIT {

  @Test
  public void jsonRest() {

    when().
        get("/helloworld-rs/rest/json").
        then().
        statusCode(200).
        body("result", equalTo("Hello World!"));
  }



}


