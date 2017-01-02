package com.rizomm.ipii.steven.service;

import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.model.Product;
import com.rizomm.ipii.steven.model.ShoppingCart;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.rizomm.ipii.steven.helper.Utils.*;

/**
 * Created by steven on 17/11/2016.
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 1000)
@Remote
public class ShoppingCartService implements IShoppingCartService {

    private List<ShoppingCart> listShoppingCart = new ArrayList<>();

    @Override
    public Map<String, Object> addProductCart(String jsonString, IProductDao PD) {
        Map<String, Object> result = new HashMap();
        int newQty = 1;
        try {
            JSONObject json = new JSONObject(jsonString);

            if (isEmpty(json, "id")) {
                return generateMessageError400("L'id du produit est vide !");
            } else if (!isInt(json.getString("id"))) {
                return generateMessageError400("L'id doit être un chiffre !");
            } else if (json.getInt("id") < 0) {
                return generateMessageError400("L'id doit être un chiffre positif !");
            }

            if (isEmpty(json, "quantity")) {
                return generateMessageError400("La quantité ne doit pas être vide, il faut indiquer soit +1 ou -1 !");
            } else if (!isInt(json.getString("quantity"))) {
                return generateMessageError400("La quantité doit être un chiffre, il faut indiquer soit +1 ou -1 !");
            } else if (json.getInt("quantity") != 1 && json.getInt("quantity") != -1) {
                return generateMessageError400("La quantité doit être +1 ou -1 !");
            }

            Product product = PD.findProductById(json.getInt("id"));

            if (isEmpty(product)) {
                return generateMessageError400("Le produit n'existe pas !");
            }

            newQty = json.getInt("quantity");
            boolean existCart = false;
            for (ShoppingCart shoppingCart : listShoppingCart) {
                if (shoppingCart.getProduct().getId() == product.getId()) {
                    newQty = shoppingCart.getQuantity() + newQty;
                    if (newQty <= 0) {
                        return deleteProductToCart(jsonString);
                    } else {
                        shoppingCart.setQuantity(newQty);
                    }
                    existCart = true;
                    break;
                }
            }

            if (!existCart) {
                if(newQty == -1){
                    return generateMessageError400("Vous ne pouvez pas diminuer la quantité d'un produit qui n'est pas dans votre panier");
                }
                listShoppingCart.add(new ShoppingCart(product, newQty));
            }

        } catch (JSONException e) {
            return generateMessageError400("Le format de la requête n'est pas respecté !");
        } catch (Exception e) {
            return generateMessageError400("Aie, une erreur est survenue !");
        }

        return generateMessageSuccess201("Produit ajouté au panier avec succés. Il y a " + newQty + " quantité de ce produit dans votre panier");
    }

    @Override
    public Map<String, Object> deleteProductToCart(String jsonString) {
        Map<String, Object> result = new HashMap();

        try {

            JSONObject json = new JSONObject(jsonString);

            if (isEmpty(json, "id")) {
                return generateMessageError400("L'id du produit est vide !");
            } else if (!isInt(json.getString("id"))) {
                return generateMessageError400("L'id doit être un chiffre !");
            } else if (json.getInt("id") < 0) {
                return generateMessageError400("L'id doit être un chiffre positif !");
            }


            boolean existCart = false;
            ShoppingCart shoppingCartToDelete = null;
            for (ShoppingCart shoppingCart : listShoppingCart) {
                if (shoppingCart.getProduct().getId() == json.getInt("id")) {
                    shoppingCartToDelete = shoppingCart;
                    existCart = true;
                }
            }

            if (!existCart) {
                return generateMessageError400("Ce produit n'existe pas dans votre panier vueillez recharger votre panier.");
            }else{
                listShoppingCart.remove(shoppingCartToDelete);
            }

        } catch (Exception e) {
            return generateMessageError400("Aie, une erreur est survenue !");
        }

        return generateMessageSuccess201("Produit supprimé avec succés.");
    }

    @Override
    public JSONObject getCart(IProductDao PD) {
        List<ShoppingCart> listShoppingCartToDelete = new ArrayList<>();
        JSONObject jsonCart = new JSONObject();

        try {
            JSONArray arrayProducts = new JSONArray();
            double totalCart = 0d;

            for (ShoppingCart shoppingCart : listShoppingCart) {
                Product product = shoppingCart.getProduct();
                if(isNotEmpty(PD.findProductById(product.getId()))){
                    JSONObject jsonProduct = convertShoppingCartToJson(shoppingCart, PD);
                    arrayProducts.put(jsonProduct);
                    totalCart = totalCart + jsonProduct.getDouble("totalDouble");
                }else{
                    listShoppingCartToDelete.add(shoppingCart);
                }
            }

            for (ShoppingCart shoppingCartToDelete : listShoppingCartToDelete) {
                listShoppingCart.remove(shoppingCartToDelete);
            }

            jsonCart.put("products", arrayProducts);
            jsonCart.put("total", convertDoubleToStringWithDixieme(totalCart));
            jsonCart.put("qty", listShoppingCart.size());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonCart;
    }

    private JSONObject convertShoppingCartToJson(ShoppingCart shoppingCart, IProductDao PD) throws JSONException {
        JSONObject jsonproduct = PD.convertProductToJson(shoppingCart.getProduct());
        double totalProduct = shoppingCart.getQuantity() * shoppingCart.getProduct().getPrice();
        jsonproduct.put("qty", shoppingCart.getQuantity());
        jsonproduct.put("total", convertDoubleToStringWithDixieme(totalProduct));
        jsonproduct.put("totalDouble", totalProduct);

        return jsonproduct;

    }


    @Override
    public boolean payer() {

        return false;
    }
}
