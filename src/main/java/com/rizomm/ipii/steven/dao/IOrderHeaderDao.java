package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.OrderHeader;

import javax.ejb.Remote;

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
}
