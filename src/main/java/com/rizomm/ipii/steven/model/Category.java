package com.rizomm.ipii.steven.model;


import javax.ejb.Remote;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull(message = "The label can't be empty")
    @Size(min = 1, max = 50, message = "Label should be between 4 and 50")
    private String label;

    public Category() {
    }

    public Category(String label) {
        this.label = label;
    }

    public Category(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
