package com.rizomm.ipii.steven.rest;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;
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

@Path("/category")
@Named
@RequestScoped
public class CategoryRest {

    @EJB
    private ICategoryDao CD;


    @POST
    @Produces("application/json")
    public Response addProductCategory(final String categoryString) {
        Map<String, Object> result = CD.convertJsonToCategoryToCreate(categoryString, true);

        if (!((boolean) result.get("ERROR"))) {
            Category categoryResult = (Category) result.get("CATEGORY");
            if (Utils.isNotEmpty(categoryResult.getId()) && Utils.isNotEmpty(CD.findCategoryById(categoryResult.getId()))) {
                result = Utils.generateMessageError400("La categorie existe déja, utiliser la methode PUT pour le modifier.");
            } else {
                Category category = CD.createCategory(categoryResult);
                result = Utils.generateMessageSuccess201("Category créé avec l'id : " + category.getId());
            }
        }

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    @PUT
    @Produces("application/json")
    public Response updateProductCategory(final String categoryString) {
        Map<String, Object> result = CD.convertJsonToCategoryToUpdate(categoryString);

        if (!((boolean) result.get("ERROR"))) {
            CD.updateCategory((Category) result.get("CATEGORY"));
            result = Utils.generateMessageSuccess200("Categorie modifié avec succés");
        }

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

    @GET
    @Produces("application/json")
    public Response getAllCategory() {


        final List<Category> listCategory = CD.findAllCategory();

        final JSONObject jsonCategory = CD.convertCategorysToJson(listCategory);

        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonCategory);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

}
