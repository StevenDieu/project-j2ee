package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.service.IShoppingCartService;
import jdk.nashorn.internal.runtime.Context;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.SSLSessionContext;
import javax.rmi.PortableRemoteObject;
import java.io.Serializable;
import java.util.Map;

@Named
@ManagedBean
@RequestScoped
public class CartController implements Serializable {

    @EJB
    private IProductDao PD;

    @EJB
    private IShoppingCartService scs;

    private int idProduct;

    private static final long serialVersionUID = 1L;

    private String messageText = "";

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessage() {
        return messageText;
    }

    public void addProductCart(int qty) {
        try {
            IShoppingCartService scs = getiShoppingCartService();

            messageText = scs.addProductCart(idProduct,qty, PD);
        } catch (NamingException e) {
            e.printStackTrace();
        }
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

    private IShoppingCartService getiShoppingCartService() throws NamingException {
        InitialContext ctx = new InitialContext();
        FacesContext context = FacesContext.getCurrentInstance();
        IShoppingCartService scs = (IShoppingCartService) context.getExternalContext().getSessionMap().get("cart");

        if(Utils.isEmpty(context.getExternalContext().getSessionMap().get("cart"))){
            Object ejb = ctx.lookup("java:global/steven-1.0.0-SNAPSHOT/ShoppingCartService");
            scs = (IShoppingCartService) PortableRemoteObject.narrow(ejb, IShoppingCartService.class);
            context.getExternalContext().getSessionMap().put("cart", scs);
        }
        return scs;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}