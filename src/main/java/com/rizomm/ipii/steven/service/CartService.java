package com.rizomm.ipii.steven.service;

import com.rizomm.ipii.steven.model.Product;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.List;

/**
 * Created by steven on 17/11/2016.
 */
@Stateful
@Remote
public class CartService {

    private String id;
    private List<Product> listProduct;

    public CartService getCart() {

        return null;
    }

    public boolean payer() {

        return false;
    }
}
