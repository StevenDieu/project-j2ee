package com.rizomm.ipii.steven.model;

import com.rizomm.ipii.steven.model.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2016-12-10T01:06:51")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Float> price;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Integer> stock;
    public static volatile SingularAttribute<Product, Category> idCategory;
    public static volatile SingularAttribute<Product, String> url;

}