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

    /**
     * Method addProductCart to add a product in cart with quantity
     *
     * @param id  of type int
     * @param qty of type int
     * @param PD  of type IProductDao
     * @return String
     */
    String addProductCart(int id, int qty, IProductDao PD);

    /**
     * Method deleteProductToCart to delete a product in cart
     *
     * @param id of type int
     * @return String
     */
    String deleteProductToCart(int id);

    /**
     * Method getListProductForOrder  to get liste product for create order
     *
     * @param PD of type IProductDao
     * @return List<Product>
     */
    List<Product> getListProductForOrder(IProductDao PD);

    /**
     * Method getTotalPriceString returns the totalPriceString of this IShoppingCartService object.
     *
     * @return the totalPriceString (type String) of this IShoppingCartService object.
     */
    String getTotalPriceString();

    /**
     * Method getTotalPrice returns the totalPrice of this IShoppingCartService object.
     *
     * @return the totalPrice (type Double) of this IShoppingCartService object.
     */
    Double getTotalPrice();

    /**
     * Method getQuantityCart returns the quantityCart of this IShoppingCartService object.
     *
     * @return the quantityCart (type int) of this IShoppingCartService object.
     */
    int getQuantityCart();

    /**
     * Method getListShoppingCart returns the listShoppingCart of this IShoppingCartService object.
     *
     * @return the listShoppingCart (type List<ShoppingCart>) of this IShoppingCartService object.
     */
    List<ShoppingCart> getListShoppingCart();

    /**
     * Method setListShoppingCart sets the listShoppingCart of this IShoppingCartService object.
     *
     * @param listShoppingCart the listShoppingCart of this IShoppingCartService object.
     */
    void setListShoppingCart(List<ShoppingCart> listShoppingCart);
}
