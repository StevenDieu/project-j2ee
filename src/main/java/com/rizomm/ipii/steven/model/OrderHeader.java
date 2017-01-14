package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import static com.rizomm.ipii.steven.model.OrderHeader.*;

/**
 * Created by steven on 17/11/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = FIND_ALL, query = "select c from OrderHeader c order by c.id asc"),
})
public class OrderHeader extends AbstractTimestampEntity implements Serializable {

    public static final String FIND_ALL = "Product.findAllorder";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_HEADER_SEQ")
    @SequenceGenerator(name = "ORDER_HEADER_SEQ", sequenceName = "order_header_seq", allocationSize = 1)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shopping_cart_fk")
    private List<ShoppingCart> shoppingCarts;

    @NotNull(message = "The total can't be empty")
    @Min(value = 0, message = "The total can't be negative")
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
     * @param shoppingCarts of type List<ShoppingCart>
     * @param total    of type double
     */
    public OrderHeader(int id, List<ShoppingCart> shoppingCarts, double total) {
        this.id = id;
        this.shoppingCarts = shoppingCarts;
        this.total = total;
    }

    /**
     * Constructor OrderHeader creates a new OrderHeader instance.
     *
     * @param shoppingCarts of type List<Product>
     * @param total    of type double
     */
    public OrderHeader(List<ShoppingCart> shoppingCarts, double total) {
        this.shoppingCarts = shoppingCarts;
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
     * Method getShoppingCarts returns the products of this OrderHeader object.
     *
     * @return the products (type List<Product>) of this OrderHeader object.
     */
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    /**
     * Method setShoppingCarts sets the products of this OrderHeader object.
     *
     * @param shoppingCarts the products of this OrderHeader object.
     */
    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
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
