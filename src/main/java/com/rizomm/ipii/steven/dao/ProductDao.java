package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rizomm.ipii.steven.helper.Utils.*;
import static com.rizomm.ipii.steven.model.Product.*;

/**
 * Created by steven on 17/11/2016.
 */
@Stateless
@Remote
@Named
public class ProductDao implements IProductDao, Serializable {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;

    @Override
    public Product createProduct(Product product) {
        if (isNotEmpty(product.getName())) {
            em.persist(product);
            if(isNotTest){
                em.flush();
            }
            return product;
        }
        return null;
    }

    @Override
    public Product findProductById(int idProduct) {
        Product findProduct = em.find(Product.class, idProduct);
        return findProduct;
    }

    @Override
    public List<Product> findAllProduct() {
        TypedQuery<Product> query = em.createNamedQuery(FIND_ALL, Product.class);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    @Override
    public List<Product> findAllProductByPage(int start, int limit) {
        TypedQuery<Product> query = em.createNamedQuery(FIND_ALL, Product.class)
                .setFirstResult(start)
                .setMaxResults(limit);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    @Override
    public List<Product> findAllProductByPageAndCategory(int start, int limit, int category) {
        TypedQuery<Product> query = em.createNamedQuery(FIND_ALL_BY_CATEGORY, Product.class)
                .setFirstResult(start)
                .setMaxResults(limit)
                .setParameter("idCategory", category);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    @Override
    public int countAllProduct() {
        TypedQuery<Integer> query = em.createNamedQuery(COUNT_ALL, Integer.class);
        if (isNotTest) {
            em.joinTransaction();
        }
        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public void deleteAllProduct() {
        em.createNamedQuery(DELETE_ALL, Product.class).executeUpdate();
    }

    @Override
    public Boolean deleteProductById(int idProduct) {
        Product product = em.find(Product.class, idProduct);
        if (isNotEmpty(product)) {
            return deleteProduct(product);
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(Product product) {
        em.remove(product);
        Product findProduct = em.find(Product.class, product.getId());
        if (isEmpty(findProduct)) {
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(Product product) {
        return em.merge(product);
    }

    @Override
    public Map<String, Object> convertJsonToProduct(String jsonString, ICategoryDao CD) {

        Map<String, Object> result = new HashMap();
        Product product = new Product();

        try {
            JSONObject json = new JSONObject(jsonString);

            if (json.has("category") && !json.isNull("category")) {
                Map<String, Object> resultCategory = CD.convertJsonToProduct(json.getString("category"));

                if((boolean) resultCategory.get("ERROR")){
                    return resultCategory;
                }

                Category category = (Category) resultCategory.get("CATEGORY");

                if(category.getId() == 0){
                    category.setId(CD.createCategory(category).getId());
                }else{
                    int idCategory = category.getId();
                    category = CD.findCategoryById(idCategory);
                    if(category == null){
                        return generateMessageError400("La catégorie avec l'id : " + idCategory + " n'éxiste pas");
                    }
                }

                product.setIdCategory(category);

            }else{
                return generateMessageError400("Une catégorie est obligatoire !");
            }

            if(isEmpty(json,"stock")){
                return generateMessageError400("Le stock est obligatoire !");
            }else if(!isInt(json.getString("stock"))){
                return generateMessageError400("Le stock doit être un chiffre !");
            }

            if(isEmpty(json,"price")){
                return generateMessageError400("Le prix est obligatoire !");
            }else if(!isFloat(json.getString("price"))){
                return generateMessageError400("Le prix doit être un nombre !");
            }

            if(isEmpty(json,"name")){
                return generateMessageError400("Le nom est obligatoire !");
            }

            if(isEmpty(json,"urlPicture")){
                return generateMessageError400("L'url de l'image est obligatoire !");
            }

            product.setStock(json.getInt("stock"));
            product.setPrice(Float.parseFloat(json.getString("price")));
            product.setName(json.getString("name"));
            product.setUrlPicture(json.getString("urlPicture"));

            if(isNotEmpty(json,"id")){
                if(!isInt(json.getString("id"))){
                    return generateMessageError400("L'id doit être un chiffre !");
                }
                product.setId(json.getInt("id"));
            }

            if(isNotEmpty(json,"description")){
                product.setDescription(json.getString("description"));
            }

            result.put("PRODUCT",product);
            result.put("ERROR",false);
        } catch (JSONException e) {
            return generateMessageError400("Le format de la requête n'est pas respecté !");
        }catch (Exception e) {
            return generateMessageError400("Aie, une erreur est survenue !");
        }

        return result;
    }

    @Override
    public JSONObject convertProductsToJson(List<Product> products) {
        JSONObject jsonProducts = new JSONObject();

        try {
            JSONArray jsonArray = new JSONArray();
            for(Product product : products){
                jsonArray.put(convertProductToJson(product));
            }
            jsonProducts.put("products", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonProducts;
    }

    @Override
    public JSONObject convertProductToJson(Product product) throws JSONException {
        JSONObject jsonproduct = new JSONObject();
        jsonproduct.put("id", product.getId());
        jsonproduct.put("description", product.getDescription());
        jsonproduct.put("idCategory", product.getIdCategory().getId());
        jsonproduct.put("labelCategory", product.getIdCategory().getLabel());
        jsonproduct.put("name", product.getName());
        jsonproduct.put("price", product.getPrice());
        jsonproduct.put("stock", product.getStock());
        jsonproduct.put("urlPicture", product.getUrlPicture());
        return jsonproduct;
    }

}
