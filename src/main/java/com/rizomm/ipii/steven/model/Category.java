package com.rizomm.ipii.steven.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Created by steven on 17/11/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAll", query = "select c from Category c"),
})
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @NotNull
    String label;

    public static final String FIND_ALL = "Category.findAll";

    public Category() {}

    public Category(String label) {
        this.label = label;
    }

    public Category(int id,String label) {
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
