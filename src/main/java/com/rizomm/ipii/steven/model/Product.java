package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import static com.rizomm.ipii.steven.model.Product.*;


/**
 * Created by steven on 17/11/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = FIND_ALL, query = "select c from Product c order by c.id asc"),
        @NamedQuery(name = FIND_ALL_BY_CATEGORY, query = "select c from Product c where c.category.id = :idCategory order by c.id asc"),
        @NamedQuery(name = COUNT_ALL, query = "select count(c) from Product c"),
        @NamedQuery(name = COUNT_ALL_BY_CATEGORY, query = "select count(c) from Product c where c.category.id = :idCategory"),
        @NamedQuery(name = DELETE_ALL, query = " delete from Product"),
})
public class Product implements Serializable {

    public static final String FIND_ALL = "Product.findAllProduct";
    public static final String COUNT_ALL = "Product.countAllProduct";
    public static final String COUNT_ALL_BY_CATEGORY = "Product.countAllProductByCategory";
    public static final String DELETE_ALL = "Product.deleteAllProduct";
    public static final String FIND_ALL_BY_CATEGORY = "Product.findAllProductByCategory";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "product_seq", allocationSize = 1, initialValue = 10)
    private int id;
    @ManyToOne
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;
    @NotNull(message = "The stock can't be empty")
    @Min(value = 0, message = "The stock can't be negative")
    private int stock;
    @NotNull(message = "The price can't be empty")
    @Min(value = 0, message = "The value can't be negative")
    private double price;
    @NotNull(message = "The name can't be empty")
    private String name;
    @NotNull(message = "The desciption can't be empty")
    @Lob
    @Column
    private String description;
    @NotNull(message = "The urlPicture can't be empty")
    private String urlPicture;

    /**
     * Constructor Product creates a new Product instance.
     */
    public Product() {
    }

    /**
     * Constructor Product creates a new Product instance.
     *
     * @param id of type int
     * @param category of type Category
     * @param stock of type int
     * @param price of type float
     * @param name of type String
     * @param description of type String
     * @param urlPicture of type String
     */
    public Product(int id, Category category, int stock, float price, String name, String description, String urlPicture) {
        this.id = id;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
        this.urlPicture = urlPicture;
    }

    /**
     * Constructor Product creates a new Product instance.
     *
     * @param category of type Category
     * @param stock of type int
     * @param price of type float
     * @param name of type String
     * @param description of type String
     * @param urlPicture of type String
     */
    public Product(Category category, int stock, float price, String name, String description, String urlPicture) {
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
        this.urlPicture = urlPicture;
    }

    /**
     * Constructor Product creates a new Product instance.
     *
     * @param category of type Category
     * @param stock of type int
     * @param price of type float
     * @param name of type String
     * @param urlPicture of type String
     */
    public Product(Category category, int stock, float price, String name, String urlPicture) {
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
        this.urlPicture = urlPicture;
    }

    /**
     * Method getId returns the id of this Product object.
     *
     *
     *
     * @return the id (type int) of this Product object.
     */
    public int getId() {
        return id;
    }

    /**
     * Method setId sets the id of this Product object.
     *
     *
     *
     * @param id the id of this Product object.
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method getCategory returns the category of this Product object.
     *
     *
     *
     * @return the category (type Category) of this Product object.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Method setCategory sets the category of this Product object.
     *
     *
     *
     * @param idCategory the category of this Product object.
     *
     */
    public void setCategory(Category idCategory) {
        this.category = idCategory;
    }

    /**
     * Method getStock returns the stock of this Product object.
     *
     *
     *
     * @return the stock (type int) of this Product object.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Method setStock sets the stock of this Product object.
     *
     *
     *
     * @param stock the stock of this Product object.
     *
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Method getPrice returns the price of this Product object.
     *
     *
     *
     * @return the price (type double) of this Product object.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method setPrice sets the price of this Product object.
     *
     *
     *
     * @param price the price of this Product object.
     *
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method getName returns the name of this Product object.
     *
     *
     *
     * @return the name (type String) of this Product object.
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName sets the name of this Product object.
     *
     *
     *
     * @param name the name of this Product object.
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method getDescription returns the description of this Product object.
     *
     *
     *
     * @return the description (type String) of this Product object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method setDescription sets the description of this Product object.
     *
     *
     *
     * @param description the description of this Product object.
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method getUrlPicture returns the urlPicture of this Product object.
     *
     *
     *
     * @return the urlPicture (type String) of this Product object.
     */
    public String getUrlPicture() {
        return urlPicture;
    }

    /**
     * Method setUrlPicture sets the urlPicture of this Product object.
     *
     *
     *
     * @param urlPicture the urlPicture of this Product object.
     *
     */
    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    /**
     * Method getShortDescription returns the shortDescription of this Product object.
     *
     *
     *
     * @return the shortDescription (type String) of this Product object.
     */
    public String getShortDescription() {
        if (this.description.length() <= 103) {
            return description;
        } else {
            return description.substring(0, 100) + "...";
        }
    }
}
