package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Category;

import java.util.List;

/**
 * Created by steven on 17/11/2016.
 */
public interface ICategoryDao {

    public boolean createCategory(Category category);
    public Category findCategoryById(int idCategory);
    public Boolean deleteCategoryById(int idCategory);
    public List<Category> findAllCategory();
    public Category updateCategory(Category category);
    public Boolean deleteCategory(Category category);
}
