package com.quantum.product;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void shouldGetProductById() {
        given()
          .pathParam("id", "myProductId")
          .when().get("/products/{id}")
          .then()
             .statusCode(200)
             .body("id", is("myProductId"),
                     "name", is("Headphone Plus"),
                     "price", is(50)
             );
    }

}