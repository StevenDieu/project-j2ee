package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.OrderHeader;
import com.rizomm.ipii.steven.model.ShoppingCart;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.rizomm.ipii.steven.helper.Utils.isEmpty;
import static com.rizomm.ipii.steven.helper.Utils.isNotEmpty;
import static com.rizomm.ipii.steven.model.OrderHeader.FIND_ALL;

/**
 * Created by steven on 17/11/2016.
 */
@Stateless
public class OrderHeaderDao implements IOrderHeaderDao {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;


    /**
     * Create an order
     *
     * @param order
     * @return
     */
    @Override
    public OrderHeader createOrder(final OrderHeader order) {
        if (isNotEmpty(order.getShoppingCarts())) {
            em.persist(order);
            if (isNotTest) {
                em.flush();
            }
            return order;
        }
        return null;
    }

    /**
     * Find one order by a id
     *
     * @param id
     * @return
     */
    @Override
    public OrderHeader findOrderById(final int id) {
        final OrderHeader findOrder = em.find(OrderHeader.class, id);
        return findOrder;
    }

    /**
     * Delete order by model order
     *
     * @param order
     * @return
     */
    @Override
    public Boolean deleteOrder(final OrderHeader order) {
        em.remove(order);
        final OrderHeader findOrder = em.find(OrderHeader.class, order.getId());
        if (isEmpty(findOrder)) {
            return true;
        }
        return false;
    }

    /**
     * Method findAllOrder get all orders
     *
     * @return List<OrderHeader>
     */
    @Override
    public List<OrderHeader> findAllOrder() {
        final TypedQuery<OrderHeader> query = em.createNamedQuery(FIND_ALL, OrderHeader.class)
                ;

        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    /**
     * Method convertOrdersToJson convert all order for json
     *
     * @param listOrder of type List<OrderHeader>
     * @return JSONObject
     */
    @Override
    public JSONObject convertOrdersToJson(List<OrderHeader> listOrder, IProductDao PD) {
        final JSONObject jsonProducts = new JSONObject();

        try {
            final JSONArray jsonArray = new JSONArray();
            for (OrderHeader orderHeader : listOrder) {
                jsonArray.put(convertOrderToJson(orderHeader, PD));
            }
            jsonProducts.put("order", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonProducts;
    }

    /**
     * Method convertOrderToJson convert one order to json
     *
     * @param orderHeader of type OrderHeader
     * @param PD
     * @return JSONObject
     */
    @Override
    public JSONObject convertOrderToJson(OrderHeader orderHeader, IProductDao PD) throws JSONException {
        final JSONObject jsonOrder = new JSONObject();
        final JSONArray jsonAllProduct = new JSONArray();
        jsonOrder.put("id", orderHeader.getId());
        for (ShoppingCart shoppingCart : orderHeader.getShoppingCarts()) {
            JSONObject jsonProduct = PD.convertProductToJson(shoppingCart.getProduct());
            jsonProduct.put("Quantity", shoppingCart.getQuantity());
            jsonProduct.put("Total product", shoppingCart.getTotalPrice());
            jsonAllProduct.put(jsonProduct);
        }
        jsonOrder.put("Product", jsonAllProduct);
        jsonOrder.put("Total order", orderHeader.getTotal());
        jsonOrder.put("Created", orderHeader.getCreated());
        jsonOrder.put("Updated", orderHeader.getUpdated());
        return jsonOrder;
    }

}
