package com.rizomm.ipii.steven.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by steven on 17/11/2016.
 */

@Path("/product")
public class Product {

    @POST
    public String addProduct() {

        return "H2G2";
    }

}
