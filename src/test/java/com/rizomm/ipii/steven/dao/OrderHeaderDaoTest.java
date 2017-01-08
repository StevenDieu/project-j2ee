package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.AbstractPersistentTest;
import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.OrderHeader;
import com.rizomm.ipii.steven.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by steven on 17/11/2016.
 */
public class OrderHeaderDaoTest extends AbstractPersistentTest {

    private OrderHeaderDao OD = null;
    private ProductDao PD = null;
    private CategoryDao CD = null;

    @Before
    public void init() {
        OD = new OrderHeaderDao();
        OD.em = em;
        OD.isNotTest = false;
        PD = new ProductDao();
        PD.em = em;
        PD.isNotTest = false;
        CD = new CategoryDao();
        CD.em = em;
        CD.isNotTest = false;
    }

    @Test
    public void shouldCreateAOrder() throws Exception {
        OrderHeader order = new OrderHeader(1,createListProduct(),10d);
        assertNull("Order should be found", em.find(OrderHeader.class, order.getId()));

        OD.createOrder(order);
        assertNotNull("Order should not be found", em.find(OrderHeader.class, order.getId()));
        deleteAllProduct();
    }


    @Test
    public void shouldCreateAOrderEmpty() throws Exception {
        OrderHeader order = new OrderHeader(2,createListProduct(),10d);
        assertNull("Order should not be found", em.find(OrderHeader.class, order.getId()));

        order = OD.createOrder(order);
        assertNotNull("Order should not be found", em.find(OrderHeader.class, order.getId()));
        deleteAllProduct();
    }

    @Test
    public void shouldDeleteOrder() throws Exception {
        final List<Product> listProduct = createListProduct();
        OrderHeader order = new OrderHeader(3, listProduct,10d);
        OrderHeader order2 = new OrderHeader(4, listProduct,10d);
        assertNull("Order should not be found", em.find(OrderHeader.class, order.getId()));
        assertNull("Order should not be found", em.find(OrderHeader.class, order2.getId()));

        tx.begin();
        OD.createOrder(order);
        OD.createOrder(order2);

        Boolean isDeleted = OD.deleteOrder(order);
        assertTrue("Order should not be not deleted", isDeleted);
        tx.commit();

        OrderHeader returnOrder1 = OD.findOrderById(order.getId());
        OrderHeader returnOrder2 = OD.findOrderById(order2.getId());
        assertNull("Order should be not a delete Order", returnOrder1);
        assertNotNull("Order should be delete Order", returnOrder2);

        tx.begin();
        OD.deleteOrder(returnOrder2);
        tx.commit();

        assertNull("Order should be not a delete Order", returnOrder1);
        deleteAllProduct();
    }

    private List<Product> createListProduct() {
        List<Product> listProduct = new ArrayList<>();
        Category category = new Category(1, "test");
        Product product = new Product(1, category, 5, 1.F, "Name of product", "description Product", "/img/picture");
        Product product2 = new Product(2, category, 5, 1.F, "Name of product2", "description Product", "/img/picture");
        tx.begin();
        CD.createCategory(category);
        PD.createProduct(product);
        PD.createProduct(product2);
        tx.commit();
        listProduct.add(product);
        listProduct.add(product2);
        return listProduct;
    }

    private void deleteAllCategory() {
        tx.begin();
        CD.deleteAllCategory();
        tx.commit();
    }

    private void deleteAllProduct() {
        tx.begin();
        PD.deleteAllProduct();
        tx.commit();
        deleteAllCategory();
    }
}
