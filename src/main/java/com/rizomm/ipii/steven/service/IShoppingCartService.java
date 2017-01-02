package com.rizomm.ipii.steven.service;

import com.rizomm.ipii.steven.dao.IProductDao;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface IShoppingCartService extends Serializable {

    Map<String, Object> addProductCart(String jsonString, IProductDao PD);

    Map<String, Object> deleteProductToCart(String jsonString);

    JSONObject getCart(IProductDao PD);

    boolean payer();

}
