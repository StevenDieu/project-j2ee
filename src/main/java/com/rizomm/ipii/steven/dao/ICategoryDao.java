package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Category;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface ICategoryDao {

    Category createCategory(Category category);

    Category findCategoryById(int idCategory);

    void deleteAllCategory();

    Boolean deleteCategoryById(int idCategory);

    List<Category> findAllCategory();

    Category updateCategory(Category category);

    Boolean deleteCategory(Category category);

    Map<String, Object> convertJsonToCategory(String category);
}
