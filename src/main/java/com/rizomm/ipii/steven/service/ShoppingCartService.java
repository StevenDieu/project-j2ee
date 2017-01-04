package com.rizomm.ipii.steven.service;

import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.model.Product;
import com.rizomm.ipii.steven.model.ShoppingCart;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rizomm.ipii.steven.helper.Utils.*;

/**
 * Created by steven on 17/11/2016.
 */
@Stateful
@Remote
public class ShoppingCartService implements IShoppingCartService {

    private List<ShoppingCart> listShoppingCart = new ArrayList<>();

    @Override
    public String addProductCart(int id, int qty, IProductDao PD) {

        Map<String, Object> result = new HashMap();

        if (isEmpty(id)) {
            return "L'id du produit est vide !";
        } else if (id < 0) {
            return "L'id doit être un chiffre positif !";
        }

        if (isEmpty(qty)) {
            return "La quantité ne doit pas être vide, il faut indiquer soit +1 ou -1 !";
        } else if (qty != 1 && qty != -1) {
            return "La quantité doit être +1 ou -1 !";
        }

        Product product = PD.findProductById(id);

        if (isEmpty(product)) {
            return "Le produit n'existe pas !";
        }

        boolean existCart = false;
        for (ShoppingCart shoppingCart : listShoppingCart) {
            if (shoppingCart.getProduct().getId() == product.getId()) {
                qty = shoppingCart.getQuantity() + qty;
                if (qty <= 0) {
                    return deleteProductToCart(id);
                } else {
                    shoppingCart.setQuantity(qty);
                }
                existCart = true;
                break;
            }
        }

        if (!existCart) {
            if (qty == -1) {
                return "Vous ne pouvez pas diminuer la quantité d'un produit qui n'est pas dans votre panier";
            }
            listShoppingCart.add(new ShoppingCart(product, qty));
        }


        return "Produit ajouté au panier avec succés. Il y a " + qty + " quantité de ce produit dans votre panier";
    }

    @Override
    public String deleteProductToCart(int id) {
        Map<String, Object> result = new HashMap();

        if (isEmpty(id)) {
            return "L'id du produit est vide !";
        } else if (id < 0) {
            return "L'id doit être un chiffre positif !";
        }

        boolean existCart = false;
        ShoppingCart shoppingCartToDelete = null;
        for (ShoppingCart shoppingCart : listShoppingCart) {
            if (shoppingCart.getProduct().getId() == id) {
                shoppingCartToDelete = shoppingCart;
                existCart = true;
            }
        }

        if (!existCart) {
            return "Ce produit n'existe pas dans votre panier vueillez recharger votre panier.";
        } else {
            listShoppingCart.remove(shoppingCartToDelete);
        }


        return "Produit supprimé avec succés.";
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
                if (isNotEmpty(PD.findProductById(product.getId()))) {
                    JSONObject jsonProduct = convertShoppingCartToJson(shoppingCart, PD);
                    arrayProducts.put(jsonProduct);
                    totalCart = totalCart + jsonProduct.getDouble("totalDouble");
                } else {
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

    @Override
    public JSONObject getCartHeader(IProductDao PD) {
        List<ShoppingCart> listShoppingCartToDelete = new ArrayList<>();
        JSONObject jsonCart = new JSONObject();

        try {
            JSONArray arrayProducts = new JSONArray();
            double totalCart = 0d;

            for (ShoppingCart shoppingCart : listShoppingCart) {
                Product product = shoppingCart.getProduct();
                if (isNotEmpty(PD.findProductById(product.getId()))) {
                    totalCart = totalCart + shoppingCart.getQuantity() * shoppingCart.getProduct().getPrice();
                } else {
                    listShoppingCartToDelete.add(shoppingCart);
                }
            }

            for (ShoppingCart shoppingCartToDelete : listShoppingCartToDelete) {
                listShoppingCart.remove(shoppingCartToDelete);
            }

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
