package com.rizomm.ipii.steven.model;

import javax.persistence.*;

/**
 * Created by steven on 17/11/2016.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_fk", nullable = false)
    Category idCategory;

    int stock;

    float price;

    String name;
    String description;
    String url;

}
