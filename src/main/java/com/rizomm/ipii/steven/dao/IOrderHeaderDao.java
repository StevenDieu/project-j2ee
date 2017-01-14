package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.OrderHeader;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface IOrderHeaderDao {

    /**
     * Create an order
     *
     * @param order
     * @return
     */
    OrderHeader createOrder(OrderHeader order);

    /**
     * Find one order by a id
     *
     * @param id
     * @return
     */
    OrderHeader findOrderById(int id);

    /**
     * Delete order by model order
     *
     * @param order
     * @return
     */
    Boolean deleteOrder(OrderHeader order);

    /**
     * Method findAllOrder get all orders
     *
     * @return List<OrderHeader>
     */
    List<OrderHeader> findAllOrder();

    /**
     * Method convertOrdersToJson convert all order for json
     *
     * @param listOrder of type List<OrderHeader>
     * @return JSONObject
     */
    JSONObject convertOrdersToJson(List<OrderHeader> listOrder, IProductDao PD);

    /**
     * Method convertOrderToJson convert one order to json
     *
     * @param orderHeader of type OrderHeader
     * @param PD
     * @return JSONObject
     */
    JSONObject convertOrderToJson(OrderHeader orderHeader, IProductDao PD) throws JSONException;
}
