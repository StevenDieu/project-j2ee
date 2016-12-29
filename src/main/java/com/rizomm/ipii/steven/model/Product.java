package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.rizomm.ipii.steven.model.Product.*;


/**
 * Created by steven on 17/11/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = FIND_ALL, query = "select c from Product c order by c.id asc"),
        @NamedQuery(name = FIND_ALL_BY_CATEGORY, query = "select c from Product c where c.idCategory.id = :idCategory order by c.id asc"),
        @NamedQuery(name = COUNT_ALL, query = "select count(c) from Product c"),
        @NamedQuery(name = COUNT_ALL_BY_CATEGORY, query = "select count(c) from Product c where c.idCategory.id = :idCategory"),
        @NamedQuery(name = DELETE_ALL, query = " delete from Product"),
})
public class Product implements Serializable {

    public static final String FIND_ALL = "Product.findAllProduct";
    public static final String COUNT_ALL = "Product.countAllProduct";
    public static final String COUNT_ALL_BY_CATEGORY = "Product.countAllProductByCategory";
    public static final String DELETE_ALL = "Product.deleteAllProduct";
    public static final String FIND_ALL_BY_CATEGORY = "Product.findAllProductByCategory";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "category_fk", nullable = false)
    private Category idCategory;
    @NotNull(message = "The stock can't be empty")
    @Min(value = 0, message = "The stock can't be negative")
    private int stock;
    @NotNull(message = "The price can't be empty")
    @Min(value = 0, message = "The value can't be negative")
    private double price;
    @NotNull(message = "The name can't be empty")
    private String name;
    @NotNull(message = "The desciption can't be empty")
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
