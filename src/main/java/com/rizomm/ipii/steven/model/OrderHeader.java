package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by steven on 17/11/2016.
 */
@Entity
public class OrderHeader extends AbstractTimestampEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_HEADER_SEQ")
    @SequenceGenerator(name = "ORDER_HEADER_SEQ", sequenceName = "order_header_seq", allocationSize = 1)
    private int id;
    @ManyToMany
    @JoinTable(name = "jnd_order_product", joinColumns = @JoinColumn(name = "order_fk"), inverseJoinColumns = @JoinColumn(name = "product_fk"))
    private List<Product> products;
    private double total;

    /**
     * Constructor OrderHeader creates a new OrderHeader instance.
     */
    public OrderHeader() {
    }

    /**
     * Constructor OrderHeader creates a new OrderHeader instance.
     *
     * @param id       of type int
     * @param products of type List<Product>
     * @param total    of type double
     */
    public OrderHeader(int id, List<Product> products, double total) {
        this.id = id;
        this.products = products;
        this.total = total;
    }

    /**
     * Constructor OrderHeader creates a new OrderHeader instance.
     *
     * @param products of type List<Product>
     * @param total    of type double
     */
    public OrderHeader(List<Product> products, double total) {
        this.products = products;
        this.total = total;
    }

    /**
     * Method getId returns the id of this OrderHeader object.
     *
     * @return the id (type int) of this OrderHeader object.
     */
    public int getId() {
        return id;
    }

    /**
     * Method setId sets the id of this OrderHeader object.
     *
     * @param id the id of this OrderHeader object.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method getProducts returns the products of this OrderHeader object.
     *
     * @return the products (type List<Product>) of this OrderHeader object.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Method setProducts sets the products of this OrderHeader object.
     *
     * @param products the products of this OrderHeader object.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Method getTotal returns the total of this OrderHeader object.
     *
     * @return the total (type double) of this OrderHeader object.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Method setTotal sets the total of this OrderHeader object.
     *
     * @param total the total of this OrderHeader object.
     */
    public void setTotal(double total) {
        this.total = total;
    }

}
