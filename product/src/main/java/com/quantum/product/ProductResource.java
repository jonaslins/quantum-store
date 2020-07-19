package com.quantum.product;

import com.quantum.product.domain.Product;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @POST
    public Response createProduct(Product product) {
        product.persist();
        return Response
                .status(Response.Status.CREATED)
                .entity(product)
                .build();
    }

    @GET
    @Path("/{id}")
    public Product getProductById(@PathParam("id") String id) {
        return Product.findById(new ObjectId(id));
    }

    @PUT
    @Path("/{id}")
    public Product updateProductById(@PathParam("id") String id, Product newProductInfo) {
        Product product = Product.findById(new ObjectId(id));
        product.setName(newProductInfo.getName());
        product.setDescription(newProductInfo.getDescription());
        product.update();
        return product;
    }
}