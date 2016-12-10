package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Product;

import java.util.List;

/**
 * Created by steven on 17/11/2016.
 */
public interface IProductDao {

    boolean createProduct(Product product);

    Product findProductById(int idProduct);

    List<Product> findAllProduct();

    void deleteAllProduct();

    Boolean deleteProductById(int idProduct);

    Boolean deleteProduct(Product product);

    Product updateProduct(Product product);
}
