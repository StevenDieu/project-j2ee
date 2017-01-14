package com.rizomm.ipii.steven.service;

import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.model.Product;
import com.rizomm.ipii.steven.model.ShoppingCart;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

import static com.rizomm.ipii.steven.helper.Utils.convertDoubleToStringWithDixieme;
import static com.rizomm.ipii.steven.helper.Utils.isEmpty;

/**
 * Created by steven on 17/11/2016.
 */
@Stateful
public class ShoppingCartService implements IShoppingCartService {

    private List<ShoppingCart> listShoppingCart = new ArrayList<>();

    /**
     * Method addProductCart to add a product in cart with quantity
     *
     * @param id  of type int
     * @param qty of type int
     * @param PD  of type IProductDao
     * @return String
     */
    @Override
    public String addProductCart(final int id, int qty, final IProductDao PD) {

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
                if (qty > 0 && qty <= product.getStock()) {
                    shoppingCart.setQuantity(qty);
                    shoppingCart.setTotalPrice(product.getPrice() * shoppingCart.getQuantity());
                } else {
                    if (qty >= product.getStock()) {
                        shoppingCart.setQuantity(product.getStock());
                        shoppingCart.setTotalPrice(product.getPrice() * product.getStock());
                        return "Il n'y a plus assez de stock nous avons actualisé votre panier...";
                    }
                    return "Pour supprimer un produit veuillez cliquer sur la croix";
                }
                existCart = true;
                break;
            }
        }

        if (!existCart) {
            if (qty == -1) {
                return "Vous ne pouvez pas diminuer la quantité d'un produit qui n'est pas dans votre panier";
            } else if (product.getStock() == 0) {
                return "Il n'y a plus assez de stock nous avons actualisé votre panier...";
            }

            listShoppingCart.add(new ShoppingCart(product, qty, product.getPrice()));
        }


        return "Produit ajouté au panier avec succés. Il y a " + qty + " quantité de ce produit dans votre panier";
    }

    /**
     * Method deleteProductToCart to delete a product in cart
     *
     * @param id of type int
     * @return String
     */
    @Override
    public String deleteProductToCart(final int id) {

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

    /**
     * Method getListProductForOrder  to get liste product for create order
     *
     * @param PD of type IProductDao
     * @return List<Product>
     */
    @Override
    public List<Product> getListProductForOrder(IProductDao PD) {
        List<Product> products = new ArrayList<>();
        if (!isChangeOnShoppingCart(PD)) {
            for (ShoppingCart shoppingCart : listShoppingCart) {
                Product product = shoppingCart.getProduct();
                product.setStock(product.getStock() - shoppingCart.getQuantity());
                PD.updateProduct(product);
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Method getTotalPriceString returns the totalPriceString of this IShoppingCartService object.
     *
     * @return the totalPriceString (type String) of this IShoppingCartService object.
     */
    @Override
    public String getTotalPriceString() {
        double totalPriceCart = 0d;
        for (ShoppingCart shoppingCart : listShoppingCart) {
            totalPriceCart = totalPriceCart + shoppingCart.getProduct().getPrice() * shoppingCart.getQuantity();
        }
        return convertDoubleToStringWithDixieme(totalPriceCart);
    }

    /**
     * Method getTotalPrice returns the totalPrice of this IShoppingCartService object.
     *
     * @return the totalPrice (type Double) of this IShoppingCartService object.
     */
    @Override
    public Double getTotalPrice() {
        double totalPriceCart = 0d;
        for (ShoppingCart shoppingCart : listShoppingCart) {
            totalPriceCart = totalPriceCart + shoppingCart.getProduct().getPrice() * shoppingCart.getQuantity();
        }
        return totalPriceCart;
    }

    /**
     * Method getQuantityCart returns the quantityCart of this IShoppingCartService object.
     *
     * @return the quantityCart (type int) of this IShoppingCartService object.
     */
    @Override
    public int getQuantityCart() {
        int quantityCart = 0;
        for (ShoppingCart shoppingCart : listShoppingCart) {
            quantityCart = quantityCart + shoppingCart.getQuantity();
        }
        return quantityCart;
    }

    /**
     * Method getListShoppingCart returns the listShoppingCart of this IShoppingCartService object.
     *
     * @return the listShoppingCart (type List<ShoppingCart>) of this IShoppingCartService object.
     */
    @Override
    public List<ShoppingCart> getListShoppingCart() {
        return listShoppingCart;
    }

    /**
     * Method setListShoppingCart sets the listShoppingCart of this IShoppingCartService object.
     *
     * @param listShoppingCart the listShoppingCart of this IShoppingCartService object.
     */
    @Override
    public void setListShoppingCart(List<ShoppingCart> listShoppingCart) {
        this.listShoppingCart = listShoppingCart;
    }

    /**
     * Method isChangeOnShoppingCart ...
     *
     * @param PD of type IProductDao
     * @return boolean
     */
    private boolean isChangeOnShoppingCart(IProductDao PD) {
        Boolean isChange = false;

        for (ShoppingCart shoppingCart : listShoppingCart) {
            final Product product = PD.findProductById(shoppingCart.getProduct().getId());
            if (isEmpty(product)) {
                isChange = true;
                listShoppingCart.remove(shoppingCart);
            } else if (product.getStock() < shoppingCart.getQuantity()) {
                isChange = true;
                shoppingCart.setQuantity(product.getStock());
            }
        }

        return isChange;
    }

}
