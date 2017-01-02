package com.rizomm.ipii.steven.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Dieu on 01/01/2017.
 */
public class ShoppingCart {

    private Product product;
    private int quantity;

    public ShoppingCart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
