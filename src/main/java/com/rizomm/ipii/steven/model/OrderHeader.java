package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by steven on 17/11/2016.
 */
@Entity
public class OrderHeader implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToMany
    @JoinTable(name = "jnd_order_product",
            joinColumns = @JoinColumn(name = "order_fk"),
            inverseJoinColumns = @JoinColumn(name = "product_fk"))
    private List<Product> products;
    private double total;

    public OrderHeader() {}

    public OrderHeader(int id, List<Product> products, double total) {
        this.id = id;
        this.products = products;
        this.total = total;
    }

    public OrderHeader(List<Product> products, double total) {
        this.products = products;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}