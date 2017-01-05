package com.rizomm.ipii.steven.model;

/**
 * Created by Steven Dieu on 01/01/2017.
 */
public class ShoppingCart {

    private Product product;
    private int quantity;
    private String priceUnit;
    private String totalPrice;

    public ShoppingCart(Product product, int quantity, String priceUnit, String totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.priceUnit = priceUnit;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
