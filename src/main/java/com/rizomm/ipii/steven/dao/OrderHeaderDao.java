package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.OrderHeader;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.rizomm.ipii.steven.helper.Utils.isEmpty;
import static com.rizomm.ipii.steven.helper.Utils.isNotEmpty;

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
        if (isNotEmpty(order.getProducts())) {
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

}
