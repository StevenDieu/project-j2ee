package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Category;

import java.util.List;

/**
 * Created by steven on 17/11/2016.
 */
public interface ICategoryDao {

    boolean createCategory(Category category);
    Category findCategoryById(int idCategory);
    void deleteAllCategory();
    Boolean deleteCategoryById(int idCategory);
    List<Category> findAllCategory();
    Category updateCategory(Category category);
    Boolean deleteCategory(Category category);
}
