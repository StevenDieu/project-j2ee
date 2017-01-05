package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
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
import java.util.List;

@Named
@ManagedBean
@RequestScoped
public class CartController implements Serializable {

    @EJB
    private IProductDao PD;
    private int idProduct;
    private List<Product> products;
    private static final long serialVersionUID = 1L;
    private String message = "";

    public void addProductCart(final int qty) {
        if (idProduct != 0) {
            message = getiShoppingCartService().addProductCart(idProduct, qty, PD);
        }
    }

    public void addProductCart(final int qty,final int idProduct) {
        message = getiShoppingCartService().addProductCart(idProduct, qty, PD);
    }

    public List<ShoppingCart> getAllProductCart() {
        return getiShoppingCartService().getListShoppingCart();
    }

//    public Response deleteProductCart() throws NamingException {
//        Map<String, Object> result = SCS.deleteProductToCart(productString);
//
//        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build() ;
//    }
//
//    public Response getCart() throws NamingException {
//        Map<String, Object> result = Utils.generateMessageSuccess200(SCS.getCart(PD));
//
//        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build() ;
//    }
//
//    public Response getCartHeader() throws NamingException {
//        Map<String, Object> result = Utils.generateMessageSuccess200(SCS.getCartHeader(PD));
//
//        return Response.status((int) result.get("CODE_HTTP")).entity(result.get("MESSAGE_HTTP")).build() ;
//    }

    private IShoppingCartService getiShoppingCartService() {
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

    public void setMessageText(final String messageText) {
        this.message = messageText;
    }
}