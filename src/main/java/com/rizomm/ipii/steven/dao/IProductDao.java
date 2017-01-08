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

    Product createProduct(Product product);

    Product findProductById(int idProduct);

    List<Product> findAllProduct();

    int countAllProduct();

    int countAllProduct(int idCategory);

    void deleteAllProduct();

    Boolean deleteProductById(int idProduct);

    Boolean deleteProduct(Product product);

    Product updateProduct(Product product);

    Map<String, Object> convertJsonToProductForCreate(String jsonString, ICategoryDao CD);

    JSONObject convertProductsToJson(List<Product> products);

    JSONObject convertProductToJson(Product product) throws JSONException;

    Map<String, Object> convertJsonToProductForDelete(String jsonProduct);

    List<Product> findAllProductByPage(int start, int limit);

    List<Product> findAllProductByPageAndSortBy(int start, int limit, String sortBy, String position);

    List<Product> findAllProductByPageAndCategory(int start, int limit, int idCategory);

    List<Product> findAllProductByPageAndCategoryAndSortBy(int start, int limit, int idCategory, String sortBy, String position);

    Map<String, Object> convertJsonToProductForUpdate(String productString, ICategoryDao cd);
}
