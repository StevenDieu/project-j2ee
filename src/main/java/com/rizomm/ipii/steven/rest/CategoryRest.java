package com.rizomm.ipii.steven.rest;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @GET
    @Produces("application/json")
    public Response getAllCategory() {


        final List<Category> listCategory = CD.findAllCategory();

        final JSONObject jsonCategory = CD.convertCategorysToJson(listCategory);

        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonCategory);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
    }

}
