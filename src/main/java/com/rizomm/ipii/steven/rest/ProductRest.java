package com.rizomm.ipii.steven.rest;

import com.rizomm.ipii.steven.model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by steven on 17/11/2016.
 */

@Path("/json/product")
public class ProductRest {

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response addProduct(Product product) {
        String result = "Product created : " + product;
        return Response.status(201).entity(result).build();
    }

}
