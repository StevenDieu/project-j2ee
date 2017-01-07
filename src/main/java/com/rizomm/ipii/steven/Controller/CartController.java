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

    public void addProductCart(final int qty) {
        if (idProduct != 0) {
            message = getShoppingCartService().addProductCart(idProduct, qty, PD);
        }
    }

    public void addProductCart(final int qty, final int idProduct) {
        message = getShoppingCartService().addProductCart(idProduct, qty, PD);
    }

    public List<ShoppingCart> getAllProductCart() {
        return getShoppingCartService().getListShoppingCart();
    }

    public void deleteProductCart(final int idProduct) {
        message = getShoppingCartService().deleteProductToCart(idProduct);
    }

    public String getTotalPriceCart() {
        return getShoppingCartService().getTotalPriceString();
    }

    public int getQtyCart() {
        return getShoppingCartService().getQuantityCart();
    }


    public void createOrder() {
        List<Product> listShoppingCart = getShoppingCartService().getListProductForOrder(PD);
        Double totalPrice = getShoppingCartService().getTotalPrice();
        if(listShoppingCart.size() > 0){
            OrderHeader orderHeader = new OrderHeader(listShoppingCart,totalPrice);
            orderHeader = OHD.createOrder(orderHeader);
            getShoppingCartService().setListShoppingCart(new ArrayList<ShoppingCart>());
            message = "Votre numéros de commande est : " + orderHeader.getId();

        }else{
            message = "Votre panier est vide ou à été modifié";
        }
    }

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

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(final int idProduct) {
        this.idProduct = idProduct;
    }

    public String getMessage() {
        return message;
    }

}