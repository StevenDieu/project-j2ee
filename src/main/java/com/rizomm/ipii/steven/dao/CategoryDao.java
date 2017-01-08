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
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rizomm.ipii.steven.helper.Utils.*;
import static com.rizomm.ipii.steven.model.Category.DELETE_ALL;
import static com.rizomm.ipii.steven.model.Category.FIND_ALL;

/**
 * Created by steven on 17/11/2016.
 */

@Stateless
@Named
public class CategoryDao implements ICategoryDao, Serializable {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;


    /**
     * Create a category with model categeory
     *
     * @param category
     * @return
     */
    @Override
    public Category createCategory(Category category) {
        if (isNotEmpty(category.getLabel())) {
            em.persist(category);
            if (isNotTest) {
                em.flush();
            }
            return category;
        }
        return null;
    }


    /**
     * Find one category by id
     *
     * @param idCategory
     * @return
     */
    @Override
    public Category findCategoryById(final int idCategory) {
        final Category findCategory = em.find(Category.class, idCategory);
        return findCategory;
    }

    /**
     * Find all category
     *
     * @return
     */
    @Override
    public List<Category> findAllCategory() {
        final TypedQuery<Category> query = em.createNamedQuery(FIND_ALL, Category.class);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    /**
     * Delete all category (using for test)
     */
    @Override
    public void deleteAllCategory() {
        em.createNamedQuery(DELETE_ALL, Category.class).executeUpdate();
    }

    /**
     * Delete one category by id
     *
     * @param idCategory
     * @return
     */
    @Override
    public Boolean deleteCategoryById(final int idCategory) {
        final Category category = em.find(Category.class, idCategory);
        if (isNotEmpty(category)) {
            return deleteCategory(category);
        }
        return false;
    }

    /**
     * Delete one category by category
     *
     * @param category
     * @return
     */
    @Override
    public Boolean deleteCategory(final Category category) {
        em.remove(category);
        final Category findCategory = em.find(Category.class, category.getId());
        if (isEmpty(findCategory)) {
            return true;
        }

        return false;
    }

    /**
     * Update category by a model category
     *
     * @param category
     * @return
     */
    @Override
    public Category updateCategory(final Category category) {
        return em.merge(category);
    }

    /**
     * Convert the json of method rest to category to create a new category
     *
     * @param categoryString
     * @param forCreateRest
     * @return
     */
    @Override
    public Map<String, Object> convertJsonToCategoryToCreate(final String categoryString, boolean forCreateRest) {
        final Map<String, Object> result = new HashMap();
        final Category category = new Category();

        try {

            final JSONObject jsonCategory = new JSONObject(categoryString);

            if (isNotEmpty(jsonCategory, "id")) {

                final String idString = jsonCategory.getString("id");

                if (!isInt(idString)) {
                    return generateMessageError400("L'id de la Category doit être un chiffre ! ");
                } else if (jsonCategory.getInt("id") < 0) {
                    return generateMessageError400("L'id doit être un chiffre positif !");
                }

                if (forCreateRest) {
                    if (isEmpty(jsonCategory, "label")) {
                        return generateMessageError400("Le label de la catégorie est obligatoire !");
                    } else if (isTooLarge(jsonCategory, "label", 255)) {
                        return generateMessageError400("Le label de la catégorie est trop long !");
                    } else if (jsonCategory.getString("label").length() == 0) {
                        return generateMessageError400("La label de la catégorie ne peut pas être vide !");
                    }
                    category.setLabel(jsonCategory.getString("label"));
                }

                category.setId(Integer.parseInt(idString));


            } else if (isNotEmpty(jsonCategory, "label")) {

                if (isTooLarge(jsonCategory, "label", 255)) {
                    return generateMessageError400("Le label de la catégorie est trop long !");
                } else if (jsonCategory.getString("label").length() == 0) {
                    return generateMessageError400("La label de la catégorie ne peut pas être vide !");
                }

                category.setLabel(jsonCategory.getString("label"));
            } else {
                return generateMessageError400("La category est mal paramétrée ! ");
            }

            result.put("ERROR", false);
            result.put("CATEGORY", category);
        } catch (JSONException e) {
            return generateMessageError400(e.getMessage());
        }


        return result;
    }

    /**
     * Convert the json of method rest to category to update a new category
     *
     * @param categoryString
     * @return
     */
    @Override
    public Map<String, Object> convertJsonToCategoryToUpdate(String categoryString) {
        final Map<String, Object> result = new HashMap();

        try {

            final JSONObject jsonCategory = new JSONObject(categoryString);

            if (isEmpty(jsonCategory, "id")) {
                return generateMessageError400("L'id est obligatoire pour la modification !");
            } else if (!isInt(jsonCategory.getString("id"))) {
                return generateMessageError400("L'id doit être un chiffre !");
            } else if (jsonCategory.getInt("id") < 0) {
                return generateMessageError400("L'id doit être un chiffre positif !");
            }

            final Category category = findCategoryById(jsonCategory.getInt("id"));
            if (Utils.isEmpty(category)) {
                return Utils.generateMessageError400("La category n'existe pas, utiliser la méthode POST pour l'ajouter.");
            }

            if (isNotEmpty(jsonCategory, "label")) {

                if (isTooLarge(jsonCategory, "label", 255)) {
                    return generateMessageError400("Le label de la catégorie est trop long !");
                } else if (jsonCategory.getString("label").length() == 0) {
                    return generateMessageError400("La label de la catégorie ne peut pas être vide !");
                }

                category.setLabel(jsonCategory.getString("label"));
            }

            result.put("ERROR", false);
            result.put("CATEGORY", category);
        } catch (JSONException e) {
            return generateMessageError400(e.getMessage());
        }

        return result;
    }

    /**
     * Convert multiple category to the json
     *
     * @param listCategory
     * @return
     */
    @Override
    public JSONObject convertCategorysToJson(List<Category> listCategory) {
        final JSONObject jsonProducts = new JSONObject();

        try {
            final JSONArray jsonArray = new JSONArray();
            for (Category category : listCategory) {
                jsonArray.put(convertCategoryToJson(category));
            }
            jsonProducts.put("products", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonProducts;
    }


    /**
     * Convert one category to the json
     *
     * @param category
     * @return
     * @throws JSONException
     */
    @Override
    public JSONObject convertCategoryToJson(Category category) throws JSONException {
        final JSONObject jsonproduct = new JSONObject();
        jsonproduct.put("id", category.getId());
        jsonproduct.put("label", category.getLabel());

        return jsonproduct;
    }
}
