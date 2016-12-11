package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.rizomm.ipii.steven.model.Product.DELETE_ALL;
import static com.rizomm.ipii.steven.model.Product.FIND_ALL;

/**
 * Created by steven on 17/11/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = FIND_ALL, query = "select c from Product c"),
        @NamedQuery(name = DELETE_ALL, query = " delete from Product"),
})
public class Product {

    public static final String FIND_ALL = "Product.findAllProduct";
    public static final String DELETE_ALL = "Product.deleteAllProduct";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category idCategory;
    @NotNull(message = "The stock can't be empty")
    @Min(value = 0, message = "The stock can't be negative")
    private int stock;
    @NotNull(message = "The price can't be empty")
    @Min(value = 0, message = "The value can't be negative")
    private float price;
    @NotNull(message = "The name can't be empty")
    private String name;
    private String description;
    @NotNull(message = "The urlPicture can't be empty")
    private String urlPicture;

    public Product() {
    }

    public Product(int id, Category idCategory, int stock, float price, String name, String description, String urlPicture) {
        this.id = id;
        this.idCategory = idCategory;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
        this.urlPicture = urlPicture;
    }

    public Product(int id, Category idCategory, int stock, float price, String name, String urlPicture) {
        this.id = id;
        this.idCategory = idCategory;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.urlPicture = urlPicture;
    }

    public Product(Category idCategory, int stock, float price, String name, String description, String urlPicture) {
        this.idCategory = idCategory;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
        this.urlPicture = urlPicture;
    }

    public Product(Category idCategory, int stock, float price, String name, String urlPicture) {
        this.idCategory = idCategory;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
        this.urlPicture = urlPicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
