package com.rizomm.ipii.steven.service;

import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.model.Product;
import com.rizomm.ipii.steven.model.ShoppingCart;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rizomm.ipii.steven.helper.Utils.convertDoubleToStringWithDixieme;
import static com.rizomm.ipii.steven.helper.Utils.isEmpty;

/**
 * Created by steven on 17/11/2016.
 */
@Stateful
@Remote
public class ShoppingCartService implements IShoppingCartService {

    private List<ShoppingCart> listShoppingCart = new ArrayList<>();

    @Override
    public String addProductCart(final int id, int qty, final IProductDao PD) {

        final Map<String, Object> result = new HashMap();

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

        final Product product = PD.findProductById(id);

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
                    shoppingCart.setPriceUnit(convertDoubleToStringWithDixieme(product.getPrice()));
                    shoppingCart.setTotalPrice(convertDoubleToStringWithDixieme(product.getPrice() * shoppingCart.getQuantity()));
                }
                existCart = true;
                break;
            }
        }

        if (!existCart) {
            if (qty == -1) {
                return "Vous ne pouvez pas diminuer la quantité d'un produit qui n'est pas dans votre panier";
            }

            final String priceString = convertDoubleToStringWithDixieme(product.getPrice());
            listShoppingCart.add(new ShoppingCart(product, qty, priceString, priceString));
        }


        return "Produit ajouté au panier avec succés. Il y a " + qty + " quantité de ce produit dans votre panier";
    }

    @Override
    public String deleteProductToCart(final int id) {
        final Map<String, Object> result = new HashMap();

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
    public List<ShoppingCart> getListShoppingCart() {
        return listShoppingCart;
    }

    @Override
    public boolean payer() {

        return false;
    }


}
