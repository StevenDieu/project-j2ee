package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.IOrderHeaderDao;
import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.OrderHeader;
import com.rizomm.ipii.steven.model.Product;
import com.rizomm.ipii.steven.model.ShoppingCart;
import com.rizomm.ipii.steven.service.IShoppingCartService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CartController
 *
 * @author steven
 *         Created on 09/01/2017
 */
@Named
@ManagedBean
@RequestScoped
public class CartController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private IProductDao PD;
    @EJB
    private IOrderHeaderDao OHD;
    private int idProduct;
    private String message = "";

    /**
     * Method addProductCart to adding one product to cart with quantity in parameters
     *
     * @param qty of type int
     */
    public void addProductCart(final int qty) {
        if (idProduct != 0) {
            message = getShoppingCartService().addProductCart(idProduct, qty, PD);
        }
    }

    /**
     * Method addProductCart to adding on product to cart with quantity and id product in parameters
     *
     * @param qty       of type int
     * @param idProduct of type int
     */
    public void addProductCart(final int qty, final int idProduct) {
        message = getShoppingCartService().addProductCart(idProduct, qty, PD);
    }

    /**
     * Method deleteProductCart to delete product in cart
     *
     * @param idProduct of type int
     */
    public void deleteProductCart(final int idProduct) {
        message = getShoppingCartService().deleteProductToCart(idProduct);
    }


    /**
     * Method createOrder to create an order with current cart
     */
    public void createOrder() {
        List<ShoppingCart> listShoppingCart = getShoppingCartService().getListShoppingCart();
        Double totalPrice = getShoppingCartService().getTotalPrice();
        if (listShoppingCart.size() > 0) {
            OrderHeader orderHeader = new OrderHeader(listShoppingCart, totalPrice);
            orderHeader = OHD.createOrder(orderHeader);
            getShoppingCartService().setListShoppingCart(new ArrayList<ShoppingCart>());
            message = "Votre numéros de commande est : " + orderHeader.getId();
        } else {
            message = "Votre panier est vide ou à été modifié";
        }
    }

    /**
     * Method convertDoubleToStringWithDixieme
     *
     * @param d of type double
     * @return String
     */
    public String convertDoubleToStringWithDixieme(Double d) {
        return Utils.convertDoubleToStringWithDixieme(d);
    }


    /**
     * Method getAllProductCart returns the allProductCart of this CartController object.
     *
     * @return the allProductCart (type List<ShoppingCart>) of this CartController object.
     */
    public List<ShoppingCart> getAllProductCart() {
        return getShoppingCartService().getListShoppingCart();
    }

    /**
     * Method getTotalPriceCart returns the totalPriceCart of this CartController object.
     *
     * @return the totalPriceCart (type String) of this CartController object.
     */
    public String getTotalPriceCart() {
        return getShoppingCartService().getTotalPriceString();
    }

    /**
     * Method getQtyCart returns the qtyCart of this CartController object.
     *
     * @return the qtyCart (type int) of this CartController object.
     */
    public int getQtyCart() {
        return getShoppingCartService().getQuantityCart();
    }

    /**
     * Method getShoppingCartService returns the shoppingCartService of this CartController object.
     *
     * @return the shoppingCartService (type IShoppingCartService) of this CartController object.
     */
    private IShoppingCartService getShoppingCartService() {
        try {
            final FacesContext context = FacesContext.getCurrentInstance();
            IShoppingCartService scs = (IShoppingCartService) context.getExternalContext().getSessionMap().get("cart");

            if (Utils.isEmpty(context.getExternalContext().getSessionMap().get("cart"))) {
                final Object ejb = new InitialContext().lookup("java:global/steven-1.0.0-SNAPSHOT/ShoppingCartService");
                scs = (IShoppingCartService) PortableRemoteObject.narrow(ejb, IShoppingCartService.class);
                context.getExternalContext().getSessionMap().put("cart", scs);
            }
            return scs;

        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method getIdProduct returns the idProduct of this CartController object.
     *
     * @return the idProduct (type int) of this CartController object.
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * Method setIdProduct sets the idProduct of this CartController object.
     *
     * @param idProduct the idProduct of this CartController object.
     */
    public void setIdProduct(final int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Method getMessage returns the message of this CartController object.
     *
     * @return the message (type String) of this CartController object.
     */
    public String getMessage() {
        return message;
    }

}