package com.rizomm.ipii.steven.service;

import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.model.Product;
import com.rizomm.ipii.steven.model.ShoppingCart;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface IShoppingCartService extends Serializable {

    String addProductCart(int id, int qty, IProductDao PD);

    String deleteProductToCart(int id);

    List<Product> getListProductForOrder(IProductDao PD);

    String getTotalPriceString();

    Double getTotalPrice();

    int getQuantityCart();

    List<ShoppingCart> getListShoppingCart();

    void setListShoppingCart(List<ShoppingCart> listShoppingCart);
}
