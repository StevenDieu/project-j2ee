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

    /**
     * Method addProduct to create a product by product json string
     *
     * @param productString of type String
     * @return Response
     */
    @POST
    @Produces("application/json")
    public Response addProduct(final String productString) {
        Map<String, Object> result = PD.convertJsonToProductToCreate(productString, CD);

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

    /**
     * Method updateProduct to update a product by product json string
     *
     * @param productString of type String
     * @return Response
     */
    @PUT
    @Produces("application/json")
    public Response updateProduct(final String productString) {
        Map<String, Object> result = PD.convertJsonToProductForUpdate(productString, CD);

        if (!((boolean) result.get("ERROR"))) {
            PD.updateProduct((Product) result.get("PRODUCT"));
            result = Utils.generateMessageSuccess200("Produit modifié avec succés");
        }

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }


    /**
     * Method deleteProduct to delete a product by id product
     *
     * @param idProduct of type int
     * @return Response
     */
    @DELETE
    @Path("/{idProduct : \\d+}")
    @Produces("application/json")
    public Response deleteProduct(@PathParam("idProduct") int idProduct) {
        Map<String, Object> result;
        if(PD.deleteProductById(idProduct)){
            result = Utils.generateMessageSuccess200("Produit supprimé avec succés.");
        }else{
            result = Utils.generateMessageError400("Le produit n'existe pas.");
        }

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build() ;
    }

    /**
     * Method getAllProductByPage to get all product by product json string and number page
     *
     * @param productString of type String
     * @param numberPage    of type int
     * @return Response
     */
    @GET
    @Path("/{numberPage : \\d+}/page")
    @Produces("application/json")
    public Response getAllProductByPage(final String productString, @PathParam("numberPage") final int numberPage) {
        final int start = numberPage * 9;
        final int limit = numberPage + 1 * 9;
        final List<Product> listProduct = PD.findAllProductByPage(start, limit);

        final JSONObject jsonProducts = PD.convertProductsToJson(listProduct);

        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonProducts);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    /**
     * Method getAllProductByPageAndSortBy to get all product by product json string and number page and sort by
     *
     * @param productString of type String
     * @param numberPage    of type int
     * @param sortBy        of type String
     * @param position      of type String
     * @return Response
     */
    @GET
    @Path("/{numberPage : \\d+}/page/{sortBy}/{position}/sortBy")
    @Produces("application/json")
    public Response getAllProductByPageAndSortBy(final String productString
            , @PathParam("numberPage") final int numberPage
            , @PathParam("sortBy") String sortBy
            , @PathParam("position") String position) {
        final int start = numberPage * 9;
        final int limit = numberPage + 1 * 9;

        sortBy = Utils.isValidateSortByProduct(sortBy);
        position = Utils.isValidatePosition(position);

        final List<Product> listProduct = PD.findAllProductByPageAndSortBy(start, limit, sortBy, position);

        final JSONObject jsonProducts = PD.convertProductsToJson(listProduct);

        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonProducts);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    /**
     * Method getAllProductByPageAndCategory to get all product by product json string and number page and id category
     *
     * @param productString of type String
     * @param numberPage    of type int
     * @param idCategory    of type int
     * @return Response
     */
    @GET
    @Path("/{numberPage : \\d+}/page/{idCategory : \\d+}/category")
    @Produces("application/json")
    public Response getAllProductByPageAndCategory(final String productString
            , @PathParam("numberPage") final int numberPage
            , @PathParam("idCategory") final int idCategory) {
        final int start = numberPage * 9;
        final int limit = numberPage + 1 * 9;
        final List<Product> listProduct = PD.findAllProductByPageAndCategory(start, limit, idCategory);

        final JSONObject jsonProducts = PD.convertProductsToJson(listProduct);

        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonProducts);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    /**
     * Method getAllProductByPageAndCategoryAndSortBy to get all product by product json string and number page and sort by and id category
     *
     * @param productString of type String
     * @param numberPage    of type int
     * @param idCategory    of type int
     * @param sortBy        of type String
     * @param position      of type String
     * @return Response
     */
    @GET
    @Path("/{numberPage : \\d+}/page/{sortBy}/{position}/sortBy/{idCategory : \\d+}/category")
    @Produces("application/json")
    public Response getAllProductByPageAndCategoryAndSortBy(final String productString
            , @PathParam("numberPage") final int numberPage
            , @PathParam("idCategory") final int idCategory
            , @PathParam("sortBy") String sortBy
            , @PathParam("position") String position) {
        final int start = numberPage * 9;
        final int limit = numberPage + 1 * 9;

        sortBy = Utils.isValidateSortByProduct(sortBy);
        position = Utils.isValidatePosition(position);

        final List<Product> listProduct = PD.findAllProductByPageAndCategoryAndSortBy(start, limit, idCategory, sortBy, position);

        final JSONObject jsonProducts = PD.convertProductsToJson(listProduct);

        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonProducts);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    /**
     * Method getCountAllProduct to count all product by product json string
     *
     * @param productString of type String
     * @return Response
     */
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

    /**
     * Method getCountAllProductByCategory to count all product by product json string and id category
     *
     * @param productString of type String
     * @param idCategory of type int
     * @return Response
     */
    @GET
    @Path("/count/{idCategory : \\d+}/category")
    @Produces("application/json")
    public Response getCountAllProductByCategory(final String productString, @PathParam("idCategory") final int idCategory) {
        final int countProduct = PD.countAllProductByCategory(idCategory);

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
