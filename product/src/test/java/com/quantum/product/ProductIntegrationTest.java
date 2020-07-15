package com.quantum.product;

import com.quantum.product.domain.Product;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(MongoContainerResource.class)
public class ProductIntegrationTest {

    @Test
    public void shouldGetProductById() {
        Product product = new Product(
                "Headphone Plus",
                "The best headphone for gaming",
                new BigDecimal(50));
        product.persist();

        given()
          .pathParam("id", product.getId().toString())
          .when().get("/products/{id}")
          .then()
             .statusCode(200)
             .body("id", is(product.getId().toString()),
                     "name", is(product.getName()),
                     "description", is(product.getDescription()),
                     "price", is(50)
             );
    }

}