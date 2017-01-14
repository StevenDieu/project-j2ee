package com.rizomm.ipii.steven.rest;

import com.rizomm.ipii.steven.dao.IOrderHeaderDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.OrderHeader;
import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */
//@Path("/order")
//public class OrderRest {
//
//    @EJB
//    private IOrderHeaderDao OHD;
//
//    /**
//     * Method getAllOrder returns the allOrder of this OrderRest object.
//     *
//     * @return the allOrder (type Response) of this OrderRest object.
//     */
//    @GET
//    @Path("/{numberPage : \\d+}/page")
//    @Produces("application/json")
//    public Response getAllOrder() {
//        final List<OrderHeader> listProduct = OHD.findAllOrder();
//
//        final JSONObject jsonProducts = OHD.convertOrdersToJson(listProduct);
//
//        final Map<String, Object> result = Utils.generateMessageSuccess200(jsonProducts);
//
//        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build();
//    }
//}
