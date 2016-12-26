package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rizomm.ipii.steven.model.Product.DELETE_ALL;
import static com.rizomm.ipii.steven.model.Product.FIND_ALL;
import static com.rizomm.ipii.steven.helper.Utils.*;

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
    public int createProduct(Product product) {
        if (isNotEmpty(product.getName())) {
            em.persist(product);
            if(isNotTest){
                em.flush();
            }
            return product.getId();
        }
        return 0;
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
                    category.setId(CD.createCategory(category));
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
                product.setDescription(json.getString("id"));
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
}
