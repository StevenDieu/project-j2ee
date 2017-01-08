package com.rizomm.ipii.steven.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.rizomm.ipii.steven.model.Category.DELETE_ALL;
import static com.rizomm.ipii.steven.model.Category.FIND_ALL;


/**
 * Created by steven on 17/11/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = FIND_ALL, query = "select c from Category c"),
        @NamedQuery(name = DELETE_ALL, query = " delete from Category"),
})
public class Category implements Serializable {

    public static final String FIND_ALL = "Category.findAllCategory";
    public static final String DELETE_ALL = "Category.deleteAllCategory";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "category_seq", allocationSize = 5, initialValue = 10)
    private int id;
    @NotNull(message = "The label can't be empty")
    @Size(min = 1, max = 50, message = "Label should be between 4 and 50")
    private String label;

    /**
     * Constructor Category creates a new Category instance.
     */
    public Category() {
    }

    /**
     * Constructor Category creates a new Category instance.
     *
     * @param label of type String
     */
    public Category(String label) {
        this.label = label;
    }

    /**
     * Constructor Category creates a new Category instance.
     *
     * @param id    of type int
     * @param label of type String
     */
    public Category(int id, String label) {
        this.id = id;
        this.label = label;
    }

    /**
     * Method getId returns the id of this Category object.
     *
     * @return the id (type int) of this Category object.
     */
    public int getId() {
        return id;
    }

    /**
     * Method setId sets the id of this Category object.
     *
     * @param id the id of this Category object.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method getLabel returns the label of this Category object.
     *
     * @return the label (type String) of this Category object.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Method setLabel sets the label of this Category object.
     *
     * @param label the label of this Category object.
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
