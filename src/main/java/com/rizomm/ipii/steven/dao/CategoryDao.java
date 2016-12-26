package com.rizomm.ipii.steven.dao;


import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Local;
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

import static com.rizomm.ipii.steven.model.Category.DELETE_ALL;
import static com.rizomm.ipii.steven.model.Category.FIND_ALL;

/**
 * Created by steven on 17/11/2016.
 */

@Stateless
@Remote
@Named
public class CategoryDao implements ICategoryDao, Serializable {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;

    @Override
    public Category createCategory(Category category) {
        if (Utils.isNotEmpty(category.getLabel())) {
            em.persist(category);
            if(isNotTest){
                em.flush();
            }
            return category;
        }
        return null;
    }

    @Override
    public Category findCategoryById(int idCategory) {
        Category findCategory = em.find(Category.class, idCategory);
        return findCategory;
    }

    @Override
    public List<Category> findAllCategory() {
        TypedQuery<Category> query = em.createNamedQuery(FIND_ALL, Category.class);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    @Override
    public void deleteAllCategory() {
        em.createNamedQuery(DELETE_ALL, Category.class).executeUpdate();
    }

    @Override
    public Boolean deleteCategoryById(int idCategory) {
        Category category = em.find(Category.class, idCategory);
        if (Utils.isNotEmpty(category)) {
            return deleteCategory(category);
        }
        return false;
    }

    @Override
    public Boolean deleteCategory(Category category) {
        em.remove(category);
        Category findCategory = em.find(Category.class, category.getId());
        if (Utils.isEmpty(findCategory)) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> convertJsonToProduct(String categoryString) {
        JSONObject jsonCategory = null;
        Map<String, Object> result = new HashMap();
        Category category = new Category();

        try {

            jsonCategory = new JSONObject(categoryString);

            if(Utils.isNotEmpty(jsonCategory,"id")){

                String idString = jsonCategory.getString("id");

                if(!Utils.isInt(idString)){
                    return Utils.generateMessageError400("L'id de la Category doit être un chiffre ! ");
                }

                category.setId(Integer.parseInt(idString));

            }else if(Utils.isNotEmpty(jsonCategory,"label")){
                category.setLabel(jsonCategory.getString("label"));
            }else{
                return Utils.generateMessageError400("La category est mal paramétré ! ");
            }

        } catch (JSONException e) {
            return Utils.generateMessageError400(e.getMessage());
        }

        result.put("ERROR",false);
        result.put("CATEGORY",category);
        return result;

    }

    @Override
    public Category updateCategory(Category category) {
        return em.merge(category);
    }

}
