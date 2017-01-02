package com.rizomm.ipii.steven.rest;

import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.service.IShoppingCartService;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */

@Path("/cart")
@Named
@RequestScoped
public class CartRest {

    @EJB
    private IShoppingCartService SCS;

    @EJB
    private IProductDao PD;

    private static final String CART_SESSION_KEY = "shoppingCart";

    @POST
    @Produces("application/json")
    public Response addProductCart(String productString, @Context HttpServletRequest request) throws NamingException {
        HttpSession session = request.getSession();

        IShoppingCartService scs = getIShoppingCartService(session);

        Map<String, Object> result = scs.addProductCart(productString, PD);

        session.setAttribute(CART_SESSION_KEY, scs);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build() ;
    }

    @DELETE
    @Produces("application/json")
    public Response deleteProductCart(String productString, @Context HttpServletRequest request) throws NamingException {
        HttpSession session = request.getSession();

        IShoppingCartService scs = getIShoppingCartService(session);

        Map<String, Object> result = scs.deleteProductToCart(productString);

        session.setAttribute(CART_SESSION_KEY, scs);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build() ;
    }

    @GET
    @Produces("application/json")
    public Response getCart(@Context HttpServletRequest request) throws NamingException {
        HttpSession session = request.getSession();

        IShoppingCartService scs = getIShoppingCartService(session);

        Map<String, Object> result = Utils.generateMessageSuccess200(scs.getCart(PD));

        session.setAttribute(CART_SESSION_KEY, scs);

        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build() ;
    }

    private IShoppingCartService getIShoppingCartService(HttpSession session) {
        IShoppingCartService scs = (IShoppingCartService) session.getAttribute(CART_SESSION_KEY);

        if(scs == null){
            scs = SCS;
        }
        return scs;
    }

}
