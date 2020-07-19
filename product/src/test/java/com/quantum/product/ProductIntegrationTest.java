package com.quantum.product;

import com.quantum.product.domain.Product;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

@QuarkusTest
@QuarkusTestResource(MongoContainerResource.class)
public class ProductIntegrationTest {

    @BeforeEach
    public void setup() {
        Product.deleteAll();
    }

    @Test
    public void shouldCreateProduct() {
        Product product = new Product(
                "Headphone Plus",
                "The best headphone for gaming",
                new BigDecimal(50));

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when().post("/products")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("id", not(emptyString()),
                        "name", is(product.getName()),
                        "description", is(product.getDescription()),
                        "price", is(product.getPrice().intValue()));
    }

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
                .statusCode(Response.Status.OK.getStatusCode())
                .body("id", is(product.getId().toString()),
                        "name", is(product.getName()),
                        "description", is(product.getDescription()),
                        "price", is(50)
                );
    }

    @Test
    public void shouldUpdateProductById() {
        Product product = new Product(
                "Headphone Plus",
                "The best headphone for gaming",
                new BigDecimal(50));
        product.persist();

        given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"New headphone\", \"description\": \"New description\" }")
                .pathParam("id", product.getId().toString())
                .when().put("/products/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("id", is(product.getId().toString()),
                        "name", is("New headphone"),
                        "description", is("New description"),
                        "price", is(50)
                );
    }

    @Test
    public void shouldDeleteProductById() {
        Product product = new Product(
                "Headphone Plus",
                "The best headphone for gaming",
                new BigDecimal(50));
        product.persist();

        given()
                .pathParam("id", product.getId().toString())
                .when().delete("/products/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

}