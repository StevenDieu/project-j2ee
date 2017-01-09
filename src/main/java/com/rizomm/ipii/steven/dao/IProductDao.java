package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface IProductDao {

    /**
     * Method createProduct to create one product
     *
     * @param product of type Product
     * @return Product
     */
    Product createProduct(Product product);

    /**
     * Method findProductById to find one product by product id
     *
     * @param idProduct of type int
     * @return Product
     */
    Product findProductById(int idProduct);

    /**
     * Method findAllProduct all product (using for test)
     *
     * @return List<Product>
     */
    List<Product> findAllProduct();

    /**
     * Method countAllProduct get count of all product
     *
     * @return int
     */
    int countAllProduct();

    /**
     * Method countAllProductByCategory get count all product by category
     *
     * @param idCategory of type int
     * @return int
     */
    int countAllProductByCategory(int idCategory);

    /**
     * Method deleteAllProduct to delete all product (using for test)
     */
    void deleteAllProduct();

    /**
     * Method deleteProductById to delete one product by id product
     *
     * @param idProduct of type int
     * @return Boolean
     */
    Boolean deleteProductById(int idProduct);

    /**
     * Method deleteProduct to delete one product by model product
     *
     * @param product of type Product
     * @return Boolean
     */
    Boolean deleteProduct(Product product);

    /**
     * Method updateProduct update one product by model product
     *
     * @param product of type Product
     * @return Product
     */
    Product updateProduct(Product product);

    /**
     * Method findAllProductByPage finde all product by page
     *
     * @param start of type int
     * @param limit of type int
     * @return List<Product>
     */
    List<Product> findAllProductByPage(int start, int limit);

    /**
     * Method findAllProductByPageAndSortBy find all product by page and by sort by
     *
     * @param start    of type int
     * @param limit    of type int
     * @param sortBy   of type String
     * @param position of type String
     * @return List<Product>
     */
    List<Product> findAllProductByPageAndSortBy(int start, int limit, String sortBy, String position);

    /**
     * Method findAllProductByPageAndCategory find all product by page and id category
     *
     * @param start      of type int
     * @param limit      of type int
     * @param idCategory of type int
     * @return List<Product>
     */
    List<Product> findAllProductByPageAndCategory(int start, int limit, int idCategory);

    /**
     * Method findAllProductByPageAndCategoryAndSortBy find all product by page and id catogy and sort by
     *
     * @param start      of type int
     * @param limit      of type int
     * @param idCategory of type int
     * @param sortBy     of type String
     * @param position   of type String
     * @return List<Product>
     */
    List<Product> findAllProductByPageAndCategoryAndSortBy(int start, int limit, int idCategory, String sortBy, String position);

    /**
     * Method convertJsonToProductForUpdate
     *
     * @param productString of type String
     * @param cd            of type ICategoryDao
     * @return Map<String, Object>
     */
    Map<String, Object> convertJsonToProductForUpdate(String productString, ICategoryDao CD);

    /**
     * Method convertJsonToProductToCreate convert json of requeste rest to product to create a new product
     *
     * @param jsonString of type String
     * @param CD         of type ICategoryDao
     * @return Map<String, Object>
     */
    Map<String, Object> convertJsonToProductToCreate(String jsonString, ICategoryDao CD);

    /**
     * Method convertProductsToJson convert multiple product to json
     *
     * @param products of type List<Product>
     * @return JSONObject
     */
    JSONObject convertProductsToJson(List<Product> products);

    /**
     * Method convertProductToJson one product to json
     *
     * @param product of type Product
     * @return JSONObject
     * @throws JSONException when
     */
    JSONObject convertProductToJson(Product product) throws JSONException;

    /**
     * Method convertJsonToProductToDelete json of request rest to product to delete this
     *
     * @param jsonProduct of type String
     * @return Map<String, Object>
     */
    Map<String, Object> convertJsonToProductToDelete(String jsonProduct);
}
