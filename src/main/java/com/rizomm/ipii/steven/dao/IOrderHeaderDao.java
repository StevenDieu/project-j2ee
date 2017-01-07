package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.OrderHeader;

import javax.ejb.Remote;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface IOrderHeaderDao {

    OrderHeader createOrder(OrderHeader order);

    OrderHeader findOrderById(int id);

    Boolean deleteOrder(OrderHeader order);
}
