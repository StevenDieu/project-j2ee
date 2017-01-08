package com.rizomm.ipii.steven.model;

/**
 * Created by Steven Dieu on 01/01/2017.
 */
public class ShoppingCart {

    private Product product;
    private int quantity;
    private String priceUnit;
    private String totalPrice;

    /**
     * Constructor ShoppingCart creates a new ShoppingCart instance.
     *
     * @param product    of type Product
     * @param quantity   of type int
     * @param priceUnit  of type String
     * @param totalPrice of type String
     */
    public ShoppingCart(Product product, int quantity, String priceUnit, String totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.priceUnit = priceUnit;
        this.totalPrice = totalPrice;
    }

    /**
     * Method getProduct returns the product of this ShoppingCart object.
     *
     * @return the product (type Product) of this ShoppingCart object.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Method setProduct sets the product of this ShoppingCart object.
     *
     * @param product the product of this ShoppingCart object.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Method getQuantity returns the quantity of this ShoppingCart object.
     *
     * @return the quantity (type int) of this ShoppingCart object.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method setQuantity sets the quantity of this ShoppingCart object.
     *
     * @param quantity the quantity of this ShoppingCart object.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method getPriceUnit returns the priceUnit of this ShoppingCart object.
     *
     * @return the priceUnit (type String) of this ShoppingCart object.
     */
    public String getPriceUnit() {
        return priceUnit;
    }

    /**
     * Method setPriceUnit sets the priceUnit of this ShoppingCart object.
     *
     * @param priceUnit the priceUnit of this ShoppingCart object.
     */
    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    /**
     * Method getTotalPrice returns the totalPrice of this ShoppingCart object.
     *
     * @return the totalPrice (type String) of this ShoppingCart object.
     */
    public String getTotalPrice() {
        return totalPrice;
    }

    /**
     * Method setTotalPrice sets the totalPrice of this ShoppingCart object.
     *
     * @param totalPrice the totalPrice of this ShoppingCart object.
     */
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
