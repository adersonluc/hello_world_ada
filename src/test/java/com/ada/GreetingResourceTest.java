package com.ada;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    // @Test
    // public void testeInsercaoDeAluno() {
    //     given()
    //       .when().post("/alunos")
    //       .then()
    //          .statusCode(201);
    // }

}