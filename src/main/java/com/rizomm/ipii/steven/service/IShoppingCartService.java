package com.rizomm.ipii.steven.service;

import com.rizomm.ipii.steven.dao.IProductDao;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */
public interface IShoppingCartService extends Serializable {

    String addProductCart(int id, int qty, IProductDao PD);

    String deleteProductToCart(int id);

    JSONObject getCart(IProductDao PD);

    JSONObject getCartHeader(IProductDao PD);

    boolean payer();

}
