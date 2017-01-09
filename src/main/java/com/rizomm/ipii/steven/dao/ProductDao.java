package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Named
public class ProductDao implements IProductDao, Serializable {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;

    /**
     * Method createProduct to create one product
     *
     * @param product of type Product
     * @return Product
     */
    @Override
    public Product createProduct(final Product product) {
        if (isNotEmpty(product.getName())) {
            em.persist(product);
            if (isNotTest) {
                em.flush();
            }
            return product;
        }
        return null;
    }

    /**
     * Method findProductById to find one product by product id
     *
     * @param idProduct of type int
     * @return Product
     */
    @Override
    public Product findProductById(final int idProduct) {
        final Product findProduct = em.find(Product.class, idProduct);
        return findProduct;
    }

    /**
     * Method findAllProduct all product (using for test)
     *
     * @return List<Product>
     */
    @Override
    public List<Product> findAllProduct() {
        final TypedQuery<Product> query = em.createNamedQuery(FIND_ALL, Product.class);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    /**
     * Method findAllProductByPage finde all product by page
     *
     * @param start of type int
     * @param limit of type int
     * @return List<Product>
     */
    @Override
    public List<Product> findAllProductByPage(final int start, final int limit) {

        String request = "SELECT c FROM Product c ORDER BY c.price desc ";
        final Query query = em.createQuery(request)
                .setFirstResult(start)
                .setMaxResults(limit);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    /**
     * Method findAllProductByPageAndSortBy find all product by page and by sort by
     *
     * @param start    of type int
     * @param limit    of type int
     * @param sortBy   of type String
     * @param position of type String
     * @return List<Product>
     */
    @Override
    public List<Product> findAllProductByPageAndSortBy(final int start, final int limit, final String sortBy, final String position) {

        String request = "SELECT c FROM Product c ORDER BY c." + sortBy + " " + position;
        final Query query = em.createQuery(request)
                .setFirstResult(start)
                .setMaxResults(limit);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    /**
     * Method findAllProductByPageAndCategory find all product by page and id category
     *
     * @param start      of type int
     * @param limit      of type int
     * @param idCategory of type int
     * @return List<Product>
     */
    @Override
    public List<Product> findAllProductByPageAndCategory(final int start, final int limit, final int idCategory) {
        final TypedQuery<Product> query = em.createNamedQuery(FIND_ALL_BY_CATEGORY, Product.class)
                .setFirstResult(start)
                .setMaxResults(limit)
                .setParameter("idCategory", idCategory);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

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
    @Override
    public List<Product> findAllProductByPageAndCategoryAndSortBy(int start, int limit, int idCategory, String sortBy, String position) {
        String request = "select c from Product c where c.category.id = :idCategory ORDER BY c." + sortBy + " " + position;
        final Query query = em.createQuery(request)
                .setFirstResult(start)
                .setMaxResults(limit)
                .setParameter("idCategory", idCategory);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    /**
     * Method countAllProduct get count of all product
     *
     * @return int
     */
    @Override
    public int countAllProduct() {
        final TypedQuery<Integer> query = em.createNamedQuery(COUNT_ALL, Integer.class);
        if (isNotTest) {
            em.joinTransaction();
        }
        return ((Number) query.getSingleResult()).intValue();
    }

    /**
     * Method countAllProductByCategory get count all product by category
     *
     * @param idCategory of type int
     * @return int
     */
    @Override
    public int countAllProductByCategory(final int idCategory) {
        final TypedQuery<Integer> query = em.createNamedQuery(COUNT_ALL_BY_CATEGORY, Integer.class)
                .setParameter("idCategory", idCategory);
        if (isNotTest) {
            em.joinTransaction();
        }
        return ((Number) query.getSingleResult()).intValue();
    }

    /**
     * Method deleteAllProduct to delete all product (using for test)
     */
    @Override
    public void deleteAllProduct() {
        em.createNamedQuery(DELETE_ALL, Product.class).executeUpdate();
    }

    /**
     * Method deleteProductById to delete one product by id product
     *
     * @param idProduct of type int
     * @return Boolean
     */
    @Override
    public Boolean deleteProductById(final int idProduct) {
        final Product product = em.find(Product.class, idProduct);
        if (isNotEmpty(product)) {
            return deleteProduct(product);
        }
        return false;
    }

    /**
     * Method deleteProduct to delete one product by model product
     *
     * @param product of type Product
     * @return Boolean
     */
    @Override
    public Boolean deleteProduct(final Product product) {
        em.remove(product);
        final Product findProduct = em.find(Product.class, product.getId());
        if (isEmpty(findProduct)) {
            return true;
        }
        return false;
    }

    /**
     * Method updateProduct update one product by model product
     *
     * @param product of type Product
     * @return Product
     */
    @Override
    public Product updateProduct(final Product product) {
        return em.merge(product);
    }

    /**
     * Method convertJsonToProductToCreate convert json of requeste rest to product to create a new product
     *
     * @param jsonString of type String
     * @param CD         of type ICategoryDao
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> convertJsonToProductToCreate(final String jsonString, final ICategoryDao CD) {

        final Map<String, Object> result = new HashMap();
        final Product product = new Product();

        try {
            final JSONObject json = new JSONObject(jsonString);


            if (isEmpty(json, "stock")) {
                return generateMessageError400("Le stock est obligatoire !");
            } else if (!isInt(json.getString("stock"))) {
                return generateMessageError400("Le stock doit être un chiffre !");
            } else if (json.getInt("stock") < 0) {
                return generateMessageError400("Le stock doit être un chiffre positif !");
            }

            if (isEmpty(json, "price")) {
                return generateMessageError400("Le prix est obligatoire !");
            } else if (!isDouble(json.getString("price"))) {
                return generateMessageError400("Le prix doit être un nombre !");
            } else if (isNotConvertDoubleToDixieme(Double.parseDouble(json.getString("price")))) {
                return generateMessageError400("Le prix est trop grand !");
            } else if (convertDoubleToDixieme(json.getString("price")) < 0) {
                return generateMessageError400("Le prix doit être un chiffre positif !");
            }

            if (isEmpty(json, "name")) {
                return generateMessageError400("Le nom est obligatoire !");
            } else if (isTooLarge(json, "name", 255)) {
                return generateMessageError400("Le nom est trop long !");
            } else if (json.getString("name").length() == 0) {
                return generateMessageError400("Le nom est ne peut pas être vide !");
            }

            if (isEmpty(json, "urlPicture")) {
                return generateMessageError400("L'url de l'image est obligatoire !");
            } else if (isTooLarge(json, "urlPicture", 255)) {
                return generateMessageError400("L'url de l'image est trop longue !");
            } else if (json.getString("urlPicture").length() == 0) {
                return generateMessageError400("L'url de l'image ne peut pas être vide !");
            }

            if (isEmpty(json, "description")) {
                return generateMessageError400("La description est obligatoire !");
            } else if (json.getString("description").length() == 0) {
                return generateMessageError400("La description ne peut pas être vide !");
            }

            product.setStock(json.getInt("stock"));
            product.setPrice(convertDoubleToDixieme(json.getString("price")));
            product.setName(json.getString("name"));
            product.setUrlPicture("product/" + json.getString("urlPicture"));
            product.setDescription(json.getString("description"));

            if (isNotEmpty(json, "id")) {
                if (!isInt(json.getString("id"))) {
                    return generateMessageError400("L'id doit être un chiffre !");
                }
                product.setId(json.getInt("id"));
            }


            if (json.has("category") && !json.isNull("category")) {
                final Map<String, Object> resultCategory = getCategoryOfJsonProduct(CD, json, product);
                if (resultCategory != null) return resultCategory;
            } else {
                return generateMessageError400("Une catégorie est obligatoire !");
            }

            result.put("PRODUCT", product);
            result.put("ERROR", false);
        } catch (JSONException e) {
            return generateMessageError400("Le format de la requête n'est pas respecté !");
        } catch (Exception e) {
            return generateMessageError400("Aie, une erreur est survenue !");
        }

        return result;
    }


    /**
     * Method convertJsonToProductForUpdate
     *
     * @param productString of type String
     * @param CD            of type ICategoryDao
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> convertJsonToProductForUpdate(final String productString, final ICategoryDao CD) {

        final Map<String, Object> result = new HashMap();

        try {
            final JSONObject json = new JSONObject(productString);

            if (isEmpty(json, "id")) {
                return generateMessageError400("L'id est obligatoire pour la modification !");
            } else if (!isInt(json.getString("id"))) {
                return generateMessageError400("L'id doit être un chiffre !");
            }

            final Product product = findProductById(json.getInt("id"));
            if (Utils.isEmpty(product)) {
                return Utils.generateMessageError400("Le produit n'existe pas, utiliser la méthode POST pour l'ajouter.");
            }

            if (isNotEmpty(json, "stock")) {
                if (!isInt(json.getString("stock"))) {
                    return generateMessageError400("Le stock doit être un chiffre !");
                } else if (json.getInt("stock") < 0) {
                    return generateMessageError400("Le stock doit être un chiffre positif !");
                }
                product.setStock(json.getInt("stock"));
            }

            if (isNotEmpty(json, "price")) {
                if (!isDouble(json.getString("price"))) {
                    return generateMessageError400("Le prix doit être un nombre !");
                } else if (isNotConvertDoubleToDixieme(Double.parseDouble(json.getString("price")))) {
                    return generateMessageError400("Le prix est trop grand !");
                } else if (convertDoubleToDixieme(json.getString("price")) < 0) {
                    return generateMessageError400("Le prix doit être un chiffre positif !");
                }
                product.setPrice(convertDoubleToDixieme(json.getString("price")));
            }

            if (isNotEmpty(json, "name")) {
                if (isTooLarge(json, "name", 255)) {
                    return generateMessageError400("Le nom est trop long !");
                } else if (json.getString("name").length() == 0) {
                    return generateMessageError400("Le nom ne peut pas être vide !");
                }
                product.setName(json.getString("name"));
            }

            if (isNotEmpty(json, "urlPicture")) {
                if (isTooLarge(json, "urlPicture", 255)) {
                    return generateMessageError400("L'url de l'image est trop longue !");
                } else if (json.getString("urlPicture").length() == 0) {
                    return generateMessageError400("L'url de l'image ne peut pas être vide !");
                }
                product.setUrlPicture("product/" + json.getString("urlPicture"));
            }

            if (isNotEmpty(json, "description")) {
                if (json.getString("description").length() == 0) {
                    return generateMessageError400("La description ne peut pas être vide !");
                }
                product.setDescription(json.getString("description"));
            }

            if (json.has("category") && !json.isNull("category")) {
                final Map<String, Object> resultCategory = getCategoryOfJsonProduct(CD, json, product);
                if (resultCategory != null) return resultCategory;
            }

            result.put("PRODUCT", product);
            result.put("ERROR", false);
        } catch (JSONException e) {
            return generateMessageError400("Le format de la requête n'est pas respecté !");
        } catch (Exception e) {
            return generateMessageError400("Aie, une erreur est survenue !");
        }

        return result;
    }

    /**
     * Method getCategoryOfJsonProduct ...
     *
     * @param CD      of type ICategoryDao
     * @param json    of type JSONObject
     * @param product of type Product
     * @return Map<String, Object>
     * @throws JSONException when
     */
    private Map<String, Object> getCategoryOfJsonProduct(ICategoryDao CD, JSONObject json, Product product) throws JSONException {
        final Map<String, Object> resultCategory = CD.convertJsonToCategoryToCreate(json.getString("category"), false);

        if ((boolean) resultCategory.get("ERROR")) {
            return resultCategory;
        }

        Category category = (Category) resultCategory.get("CATEGORY");

        if (category.getId() == 0) {
            category.setId(CD.createCategory(category).getId());
        } else {
            int idCategory = category.getId();
            category = CD.findCategoryById(idCategory);
            if (category == null) {
                return generateMessageError400("La catégorie avec l'id : " + idCategory + " n'éxiste pas");
            }
        }

        product.setCategory(category);
        return null;
    }

    /**
     * Method convertJsonToProductToDelete json of request rest to product to delete this
     *
     * @param jsonProduct of type String
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> convertJsonToProductToDelete(final String jsonProduct) {
        final Map<String, Object> result = new HashMap();

        try {
            JSONObject json = new JSONObject(jsonProduct);

            if (isEmpty(json, "id")) {
                return generateMessageError400("L'id est obligatoire !");
            } else if (!isInt(json.getString("id"))) {
                return generateMessageError400("L'id doit être un chiffre !");
            } else if (json.getInt("id") < 0) {
                return generateMessageError400("L'id doit être un chiffre positif !");
            }

            result.put("ID", json.getInt("id"));
            result.put("ERROR", false);
        } catch (JSONException e) {
            return generateMessageError400("Le format de la requête n'est pas respecté !");
        } catch (Exception e) {
            return generateMessageError400("Aie, une erreur est survenue !");
        }

        return result;
    }

    /**
     * Method convertProductsToJson convert multiple product to json
     *
     * @param products of type List<Product>
     * @return JSONObject
     */
    @Override
    public JSONObject convertProductsToJson(final List<Product> products) {
        final JSONObject jsonProducts = new JSONObject();

        try {
            final JSONArray jsonArray = new JSONArray();
            for (Product product : products) {
                jsonArray.put(convertProductToJson(product));
            }
            jsonProducts.put("products", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonProducts;
    }

    /**
     * Method convertProductToJson one product to json
     *
     * @param product of type Product
     * @return JSONObject
     * @throws JSONException when
     */
    @Override
    public JSONObject convertProductToJson(Product product) throws JSONException {
        final JSONObject jsonproduct = new JSONObject();
        jsonproduct.put("id", product.getId());
        jsonproduct.put("description", product.getShortDescription());
        jsonproduct.put("idCategory", product.getCategory().getId());
        jsonproduct.put("labelCategory", product.getCategory().getLabel());
        jsonproduct.put("name", product.getName());
        jsonproduct.put("price", convertDoubleToStringWithDixieme(product.getPrice()));
        jsonproduct.put("stock", product.getStock());
        jsonproduct.put("urlPicture", product.getUrlPicture());
        return jsonproduct;
    }

}
