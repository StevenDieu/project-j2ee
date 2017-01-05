package com.rizomm.ipii.steven.rest;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONException;
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
    public Response addProduct(final String productString) {
        Map<String, Object> result = PD.convertJsonToProduct(productString, CD);

        if (!((boolean) result.get("ERROR"))) {
            Product productResult = (Product) result.get("PRODUCT");
            if (Utils.isNotEmpty(productResult.getId()) && Utils.isNotEmpty(PD.findProductById(productResult.getId()))) {
                result = Utils.generateMessageError400("Le produit existe déja, utiliser la methode PUT pour le modifier.");
            } else {
                Product product = PD.createProduct(productResult);
                result = Utils.generateMessageSuccess201("Produit créé avec l'id : " + product.getId() + " et avec la catégorie id : " + product.getCategory().getId());
            }
        }

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    @PUT
    @Produces("application/json")
    public Response updateProduct(final String productString) {
        Map<String, Object> result = PD.convertJsonToProduct(productString, CD);

        if (!((boolean) result.get("ERROR"))) {
            final Product productResult = (Product) result.get("PRODUCT");
            if (Utils.isNotEmpty(productResult.getId()) && Utils.isNotEmpty(PD.findProductById(productResult.getId()))) {
                PD.updateProduct(productResult);
                result = Utils.generateMessageSuccess200("Produit modifié avec succés");
            } else {
                result = Utils.generateMessageError400("Le produit n'existe pas, utiliser la méthode POST pour l'ajouter.");
            }
        }

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    @DELETE
    @Produces("application/json")
    public Response deleteProduct(final String jsonProduct) {
        Map<String, Object> result = PD.convertJsonToProductForDelete(jsonProduct);

        if (!((boolean) result.get("ERROR"))) {
            if (PD.deleteProductById((int) result.get("idProduct"))) {
                result = Utils.generateMessageSuccess200("Produit supprimé avec succés.");
            } else {
                result = Utils.generateMessageError400("Le produit n'existe pas.");
            }
        }

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    @GET
    @Path("/{numberPage : \\d+}/page")
    @Produces("application/json")
    public Response getAllProductByPage(final String productString, @PathParam("numberPage")final int numberPage) {
        final int start = numberPage * 9;
        final int limit = numberPage + 1 * 9;
        final List<Product> listProduct = PD.findAllProductByPage(start, limit);

        final JSONObject jsonProducts = PD.convertProductsToJson(listProduct);

        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonProducts);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    @GET
    @Path("/{numberPage : \\d+}/page/{idCategory : \\d+}/category")
    @Produces("application/json")
    public Response getAllProductByPageAndCategory(final String productString
            , @PathParam("numberPage")final int numberPage
            , @PathParam("idCategory")final int idCategory) {
        final int start = numberPage * 9;
        final int limit = numberPage + 1 * 9;
        final List<Product> listProduct = PD.findAllProductByPageAndCategory(start, limit, idCategory);

        final JSONObject jsonProducts = PD.convertProductsToJson(listProduct);

        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonProducts);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    @GET
    @Path("/count")
    @Produces("application/json")
    public Response getCountAllProduct(final String productString) {
        final int countProduct = PD.countAllProduct();

        final JSONObject jsonCountProducts = new JSONObject();
        try {
            jsonCountProducts.put("COUNT_PRODUCT", countProduct);
            jsonCountProducts.put("COUNT_PAGE", Math.ceil((double) countProduct / 9));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonCountProducts);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    @GET
    @Path("/count/{idCategory : \\d+}/category")
    @Produces("application/json")
    public Response getCountAllProductByCategory(final String productString, @PathParam("idCategory")final int idCategory) {
        final int countProduct = PD.countAllProduct(idCategory);

        final JSONObject jsonCountProducts = new JSONObject();
        try {
            jsonCountProducts.put("COUNT_PRODUCT", countProduct);
            jsonCountProducts.put("COUNT_PAGE", Math.ceil((double) countProduct / 9));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonCountProducts);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

}
