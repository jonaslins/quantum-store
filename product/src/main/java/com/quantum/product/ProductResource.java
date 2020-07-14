package com.quantum.product;

import com.quantum.product.domain.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") String id) {
        return new Product(
                id,
                "Headphone Plus",
                "The best headphone for gaming",
                new BigDecimal(50));
    }
}