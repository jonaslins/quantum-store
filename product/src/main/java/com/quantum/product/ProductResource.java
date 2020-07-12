package com.quantum.product;

import com.quantum.product.domain.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/products")
public class ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product hello() {
        return new Product(
                "Headphone Plus",
                "The best headphone for gaming",
                new BigDecimal(50));
    }
}