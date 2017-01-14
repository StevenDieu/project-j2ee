package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Steven Dieu on 01/01/2017.
 */
@Entity
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOPPING_CART_SEQ")
    @SequenceGenerator(name = "SHOPPING_CART_SEQ", sequenceName = "shopping_cart_seq", allocationSize = 1, initialValue = 10)
    private int id;

    @ManyToOne
    private Product product;

    @NotNull(message = "The quantity can't be empty")
    @Min(value = 0, message = "The quantity can't be negative")
    private int quantity;

    @NotNull(message = "The totalPrice can't be empty")
    @Min(value = 0, message = "The totalPrice can't be negative")
    private Double totalPrice;

    /**
     * Constructor ShoppingCart creates a new ShoppingCart instance.
     */
    public ShoppingCart() {
    }

    /**
     * Constructor ShoppingCart creates a new ShoppingCart instance.
     *
     * @param product    of type Product
     * @param quantity   of type int
    $     * @param totalPrice of type Double
     */
    public ShoppingCart(Product product, int quantity, Double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    /**
     * Method getId returns the id of this ShoppingCart object.
     *
     * @return the id (type int) of this ShoppingCart object.
     */
    public int getId() {
        return id;
    }

    /**
     * Method setId sets the id of this ShoppingCart object.
     *
     * @param id the id of this ShoppingCart object.
     */
    public void setId(int id) {
        this.id = id;
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
     * Method getTotalPrice returns the totalPrice of this ShoppingCart object.
     *
     * @return the totalPrice (type String) of this ShoppingCart object.
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Method setTotalPrice sets the totalPrice of this ShoppingCart object.
     *
     * @param totalPrice the totalPrice of this ShoppingCart object.
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
