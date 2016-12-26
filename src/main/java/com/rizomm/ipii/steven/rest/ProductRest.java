package com.rizomm.ipii.steven.rest;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.dao.ProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */

@Path("/product")
@Named
@RequestScoped
public class ProductRest {

    @EJB
    private IProductDao PD;

    @EJB
    private ICategoryDao CD;

    @POST
    @Produces("application/json")
    public Response addProduct(String productString) {
        Map<String, Object> result = PD.convertJsonToProduct(productString,CD);
        if(!((boolean) result.get("ERROR"))){
            int idProduct = PD.createProduct((Product) result.get("PRODUCT"));
            result = Utils.generateMessageSuccess201("Product créé avec l'id : " + idProduct);
        }

        return Response.status((int) result.get("CODE_HTTP")).entity((String) result.get("MESSAGE_HTTP")).build() ;

    }

    @GET
    @Path("/all")
    public Response getAllProduct(String productString) {
        List<Product> listProduct = PD.findAllProduct();

        Map<String, Object> result = Utils.generateMessageSuccess201("Tout les produits sont récupérés ");

        return Response.status((int) result.get("CODE_HTTP")).entity((String) result.get("MESSAGE_HTTP")).build() ;
    }

}
