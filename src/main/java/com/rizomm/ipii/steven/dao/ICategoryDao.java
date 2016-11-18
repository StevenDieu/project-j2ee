package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Category;

/**
 * Created by steven on 17/11/2016.
 */
public interface ICategoryDao {

    public boolean createCategoty(Category category);
    public Category findCategoty(Category category);
    public Category findCategotyById(int idCategory);
    public Boolean deleteCategotyById(int idCategory);
    public Boolean deleteCategoty(Category category);


}
