package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Category;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface ICategoryDao {

    /**
     * Create a category with model categeory
     *
     * @param category
     * @return
     */
    Category createCategory(Category category);

    /**
     * Find one category by id
     *
     * @param idCategory
     * @return
     */
    Category findCategoryById(int idCategory);

    /**
     * Delete all category (using for test)
     */
    void deleteAllCategory();

    /**
     * Delete one category by id
     *
     * @param idCategory
     * @return
     */
    Boolean deleteCategoryById(int idCategory);

    /**
     * Find all category
     *
     * @return
     */
    List<Category> findAllCategory();

    /**
     * Update category by a model category
     *
     * @param category
     * @return
     */
    Category updateCategory(Category category);

    /**
     * Delete one category by category
     *
     * @param category
     * @return
     */
    Boolean deleteCategory(Category category);

    /**
     * Convert the json of method rest to category to create a new category
     *
     * @param categoryString
     * @param forCreateRest
     * @return
     */
    Map<String, Object> convertJsonToCategoryToCreate(String categoryString, boolean forCreateRest);

    /**
     * Convert the json of method rest to category to update a new category
     *
     * @param categoryString
     * @return
     */
    Map<String, Object> convertJsonToCategoryToUpdate(String categoryString);

    /**
     * Convert multiple category to the json
     *
     * @param listCategory
     * @return
     */
    JSONObject convertCategorysToJson(List<Category> listCategory);

    /**
     * Convert one category to the json
     *
     * @param category
     * @return
     * @throws JSONException
     */
    JSONObject convertCategoryToJson(Category category) throws JSONException;


}
